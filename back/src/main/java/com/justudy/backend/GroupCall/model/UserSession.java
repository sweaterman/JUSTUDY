/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.justudy.backend.GroupCall.model;

import com.google.gson.JsonObject;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.websocket.Session;
import org.kurento.client.Continuation;
import org.kurento.client.EventListener;
import org.kurento.client.IceCandidate;
import org.kurento.client.IceCandidateFoundEvent;
import org.kurento.client.MediaPipeline;
import org.kurento.client.WebRtcEndpoint;
import org.kurento.jsonrpc.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ivan Gracia (izanmail@gmail.com)
 * @since 4.3.1
 */
public class UserSession implements Closeable {

  private static final Logger log = LoggerFactory.getLogger(UserSession.class);

  private String name;
  private final Session session;

  private final MediaPipeline pipeline;

  private final String roomName;
  private WebRtcEndpoint outgoingMedia;//final 제거
  private ConcurrentMap<String, WebRtcEndpoint> incomingMedia = new ConcurrentHashMap<>();

  private final ConcurrentHashMap<String, String> imageOnFace = new ConcurrentHashMap<String, String>() {{
    put("마리오 모자",
        "https://velog.velcdn.com/images/uiseok/post/bade4912-c2ae-49bd-81ad-d82454f64307/image.png");
    put("테스트 모자", "https://kuku-keke.com/wp-content/uploads/2020/04/2497_4.png");
  }};

  public UserSession(final String name, String roomName, final Session session,
      MediaPipeline pipeline) {

    this.pipeline = pipeline;
    this.name = name;
    this.session = session;
    this.roomName = roomName;
    this.outgoingMedia = new WebRtcEndpoint.Builder(pipeline).useDataChannels().build();

    this.outgoingMedia.addIceCandidateFoundListener(new EventListener<IceCandidateFoundEvent>() {

      @Override
      public void onEvent(IceCandidateFoundEvent event) {
        JsonObject response = new JsonObject();
        response.addProperty("id", "iceCandidate");
        response.addProperty("name", name);
        response.add("candidate", JsonUtils.toJsonObject(event.getCandidate()));
        try {
          synchronized (session) {
            session.getBasicRemote().sendText(response.toString());
          }
        } catch (IOException e) {
          log.info(e.getMessage());
        }
      }
    });


  }

  public WebRtcEndpoint getOutgoingWebRtcPeer() {
    return outgoingMedia;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Session getSession() {
    return session;
  }

  /**
   * The room to which the user is currently attending.
   *
   * @return The room
   */
  public String getRoomName() {
    return this.roomName;
  }

  public void receiveVideoFrom(UserSession sender, String sdpOffer) throws IOException {

    log.info("USER {}: ", this.name);
    log.info("connecting with {}", sender.getName());
    log.info("in room {}", this.roomName);

    log.trace("USER {}: SdpOffer for {} is {}", this.name, sender.getName(), sdpOffer);

    final String ipSdpAnswer = this.getEndpointForUser(sender).processOffer(sdpOffer);
    final JsonObject scParams = new JsonObject();
    scParams.addProperty("id", "receiveVideoAnswer");
    scParams.addProperty("name", sender.getName());
    scParams.addProperty("sdpAnswer", ipSdpAnswer);

    log.trace("USER {}: SdpAnswer for {} is {}", this.name, sender.getName(), ipSdpAnswer);
    this.sendMessage(scParams);
    log.info("gather candidates");
    this.getEndpointForUser(sender).gatherCandidates();
  }


  private WebRtcEndpoint getEndpointForUser(final UserSession sender) {
    if (sender.getName().equals(name)) {
      log.info("PARTICIPANT {}: configuring loopback", this.name);
      return outgoingMedia;
    }

    log.info("PARTICIPANT {}: receiving video from {}", this.name, sender.getName());

    WebRtcEndpoint incoming = incomingMedia.get(sender.getName());
    if (incoming == null) {
      log.info("PARTICIPANT {}: creating new endpoint for {}", this.name, sender.getName());
      incoming = new WebRtcEndpoint.Builder(pipeline).useDataChannels().build();
      incoming.addIceCandidateFoundListener(new EventListener<IceCandidateFoundEvent>() {

        @Override
        public void onEvent(IceCandidateFoundEvent event) {
          JsonObject response = new JsonObject();
          response.addProperty("id", "iceCandidate");
          response.addProperty("name", sender.getName());
          response.add("candidate", JsonUtils.toJsonObject(event.getCandidate()));
          try {
            synchronized (session) {
              session.getBasicRemote().sendText(response.toString());
            }
          } catch (IOException e) {
            log.info(e.getMessage());
          }

        }
      });

      incomingMedia.put(sender.getName(), incoming);
    }

    log.info("PARTICIPANT {}: obtained endpoint for {}", this.name, sender.getName());
    sender.getOutgoingWebRtcPeer().connect(incoming);

    return incoming;
  }

  public void cancelVideoFrom(final UserSession sender) {
    this.cancelVideoFrom(sender.getName());
  }

  public void cancelVideoFrom(final String senderName) {
    log.info("PARTICIPANT {}: canceling video reception from {}", this.name, senderName);
    final WebRtcEndpoint incoming = incomingMedia.remove(senderName);

    log.info("PARTICIPANT {}: removing endpoint for {}", this.name, senderName);
    if (incoming != null) {
      incoming.release(new Continuation<Void>() {
        @Override
        public void onSuccess(Void result) throws Exception {
//        log.trace("PARTICIPANT {}: Released successfully incoming EP for {}",
//            UserSession.this.name, senderName);
        }

        @Override
        public void onError(Throwable cause) throws Exception {
//        log.warn("PARTICIPANT {}: Could not release incoming EP for {}", UserSession.this.name,
//            senderName);
        }
      });
    }
  }

  @Override
  public void close() throws IOException {
    log.info("PARTICIPANT {}: Releasing resources", this.name);
    for (final String remoteParticipantName : incomingMedia.keySet()) {

      log.trace("PARTICIPANT {}: Released incoming EP for {}", this.name, remoteParticipantName);

      final WebRtcEndpoint ep = this.incomingMedia.get(remoteParticipantName);

      if (ep != null) {
        ep.release(new Continuation<Void>() {

          @Override
          public void onSuccess(Void result) throws Exception {
//          log.trace("PARTICIPANT {}: Released successfully incoming EP for {}",
//              UserSession.this.name, remoteParticipantName);
          }

          @Override
          public void onError(Throwable cause) throws Exception {
//          log.warn("PARTICIPANT {}: Could not release incoming EP for {}", UserSession.this.name,
//              remoteParticipantName);
          }
        });
      }
    }

    outgoingMedia.release(new Continuation<Void>() {

      @Override
      public void onSuccess(Void result) throws Exception {
//        log.trace("PARTICIPANT {}: Released outgoing EP", UserSession.this.name);
      }

      @Override
      public void onError(Throwable cause) throws Exception {
//        log.warn("USER {}: Could not release outgoing EP", UserSession.this.name);
      }
    });
  }

  public void sendMessage(JsonObject message) throws IOException {
    log.info("USER {}: Sending message {}", name, message);
    synchronized (session) {
      session.getBasicRemote().sendText(message.toString());
    }
  }

  public void addCandidate(IceCandidate candidate, String name) {
    if (this.name.compareTo(name) == 0) {
      outgoingMedia.addIceCandidate(candidate);
    } else {
      WebRtcEndpoint webRtc = incomingMedia.get(name);
      if (webRtc != null) {
        webRtc.addIceCandidate(candidate);
      }
    }
  }

  public void transferBan(String personName) throws IOException {
    final JsonObject sender = new JsonObject();
    sender.addProperty("id", "ban");
    sender.addProperty("name", personName);
    this.sendMessage(sender);
  }

  public void transferMute(String personName) throws IOException {
    final JsonObject sender = new JsonObject();
    sender.addProperty("id", "mute");
    sender.addProperty("name", personName);
    this.sendMessage(sender);
  }

  public void transferExit() throws IOException {
    final JsonObject sender = new JsonObject();
    sender.addProperty("id", "exit");
    this.sendMessage(sender);
  }
  public void transferRequestBan(String personName) throws IOException {
    final JsonObject sender = new JsonObject();
    sender.addProperty("id", "requestBanVote");
    sender.addProperty("name", personName);
    this.sendMessage(sender);
  }

  public void transferRequestMute(String personName) throws IOException {
    final JsonObject sender = new JsonObject();
    sender.addProperty("id", "requestMuteVote");
    sender.addProperty("name", personName);
    this.sendMessage(sender);
  }

  public void transferRequestExit() throws IOException {
    final JsonObject sender = new JsonObject();
    sender.addProperty("id", "requestExitVote");
    this.sendMessage(sender);
  }

  public void transferLadderResult(String value) throws IOException {
    final JsonObject sender = new JsonObject();
    sender.addProperty("id", "ladderResult");
    sender.addProperty("value", value);
    this.sendMessage(sender);

  }

  public void transferChatMessage(String personName, String message) throws IOException {
    final JsonObject sender = new JsonObject();
    sender.addProperty("id", "receiveChatMessage");
    sender.addProperty("name", personName);
    sender.addProperty("message", message);
    this.sendMessage(sender);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof UserSession)) {
      return false;
    }
    UserSession other = (UserSession) obj;
    boolean eq = name.equals(other.name);
    eq &= roomName.equals(other.roomName);
    return eq;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + name.hashCode();
    result = 31 * result + roomName.hashCode();
    return result;
  }


}
