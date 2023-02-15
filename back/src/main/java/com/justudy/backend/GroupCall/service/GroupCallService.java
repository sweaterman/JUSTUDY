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

package com.justudy.backend.GroupCall.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.justudy.backend.GroupCall.config.ServerEndpointConfig;
import com.justudy.backend.GroupCall.model.Room;
import com.justudy.backend.GroupCall.model.RoomManager;
import com.justudy.backend.GroupCall.model.UserRegistry;
import com.justudy.backend.GroupCall.model.UserSession;
import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.kurento.client.IceCandidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Gracia (izanmail@gmail.com)
 * @since 4.3.1
 */
@Service
@ServerEndpoint(value = "/groupcall", configurator = ServerEndpointConfig.class)
public class GroupCallService {

  private static final Logger log = LoggerFactory.getLogger(GroupCallService.class);

  private static final Gson gson = new GsonBuilder().create();

  @Autowired
  private RoomManager roomManager;

  @Autowired
  private UserRegistry registry;

  @OnMessage
  public void OnMessage(Session session, String message) throws Exception {
    final JsonObject jsonMessage = gson.fromJson(message, JsonObject.class);

    UserSession user = registry.getBySession(session);

    if (user != null) {
      log.info("Incoming message from user '{}': {}", user.getName(), jsonMessage);
    } else {
      log.info("Incoming message from new user: {}", jsonMessage);
    }

    switch (jsonMessage.get("id").getAsString()) {
      case "joinRoom":
        joinRoom(jsonMessage, session);
        break;
      case "receiveVideoFrom":
        final String senderName = jsonMessage.get("sender").getAsString();
        final UserSession sender = registry.getByName(senderName);
        final String sdpOffer = jsonMessage.get("sdpOffer").getAsString();

        user.receiveVideoFrom(sender, sdpOffer);
        break;
      case "leaveRoom":
        leaveRoom(user);
        break;
      case "ban":
        ban(jsonMessage.get("name").getAsString(), jsonMessage.get("room").getAsString());
        break;
      case "mute":
        mute(jsonMessage.get("name").getAsString(), jsonMessage.get("room").getAsString());
        break;
      case "exit":
        allExit(jsonMessage.get("room").getAsString());
        break;
      case "requestBan":
        requestBan(jsonMessage.get("name").getAsString(), jsonMessage.get("room").getAsString());
        break;
      case "requestMute":
        requestMute(jsonMessage.get("name").getAsString(), jsonMessage.get("room").getAsString());
        break;
      case "requestExit":
        requestExit(jsonMessage.get("room").getAsString());
        break;
      case "sendLadderResult":
        sendLadderResult(jsonMessage.get("name").getAsString(),
            jsonMessage.get("room").getAsString(), jsonMessage.get("value").getAsString());
        break;
      case "sendChat":
        sendChatMessage(jsonMessage.get("name").getAsString(),
            jsonMessage.get("room").getAsString(), jsonMessage.get("message").getAsString());
        break;
      case "onIceCandidate":
        JsonObject candidate = jsonMessage.get("candidate").getAsJsonObject();

        if (user != null) {
          IceCandidate cand = new IceCandidate(candidate.get("candidate").getAsString(),
              candidate.get("sdpMid").getAsString(), candidate.get("sdpMLineIndex").getAsInt());
          user.addCandidate(cand, jsonMessage.get("name").getAsString());
        }
        break;
      default:
        break;
    }
  }

  private void sendChatMessage(String personName, String roomName, String message)
      throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for (UserSession user : room.getParticipants()) {
      user.transferChatMessage(personName, message);
    }
  }

  private void sendLadderResult(String personName, String roomName, String value) throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for (UserSession user : room.getParticipants()) {
      if (user.getName().equals(personName)) {
        user.transferLadderResult(value);
      }
    }
  }

  private void requestExit(String roomName) throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for (UserSession user : room.getParticipants()) {
      user.transferRequestExit();
    }
  }

  private void requestBan(String personName, String roomName) throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for (UserSession user : room.getParticipants()) {
      user.transferRequestBan(personName);
    }
  }

  private void requestMute(String personName, String roomName) throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for (UserSession user : room.getParticipants()) {
      user.transferRequestMute(personName);
    }
  }

  private void ban(String personName, String roomName) throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for (UserSession user : room.getParticipants()) {
      if (user.getName().equals(personName)) {
        user.transferBan(personName);
      }
    }
  }

  private void mute(String personName, String roomName) throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for (UserSession user : room.getParticipants()) {
      if (user.getName().equals(personName)) {
        user.transferMute(personName);
      }

    }
  }

  private void allExit(String roomName) throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for (UserSession user : room.getParticipants()) {
      user.transferExit();//모두에게 exit올리기
    }
  }


  @OnOpen
  public void afterConnectionEstablished() throws Exception {
    log.info("afterConnectionEstablished...........................");

  }

  @OnClose
  public void afterConnectionClosed(Session session) throws Exception {
    log.info("afterConnectionClosed");
    UserSession user = registry.removeBySession(session);
    if (user == null) {
      log.info("......duplicate eror");
      return;
    }
    log.info("user getRoomName : {}", user.getRoomName());
    if (roomManager.getRoomState(user.getRoomName()) != null) {
      roomManager.getRoom(user.getRoomName()).leave(user);
    }
  }

  private void joinRoom(JsonObject params, Session session) throws IOException {
    final String roomName = params.get("room").getAsString();
    String name = params.get("name").getAsString();

    log.info("PARTICIPANT {}: trying to join room {}", name, roomName);

    Room room = roomManager.getRoom(roomName);

    boolean existCheck = false;
    int retryNum = 0;

    for (UserSession userSession : room.getParticipants()) {//같은 이름 찾기
      if (userSession.getName().equals(name)) {
        existCheck = true;
        break;
      }
    }

    if (existCheck) {//같은 이름 존재하면 발생
      String changName = name;
      while (existCheck) {//이름이 같지 않을 때까지 이름 섞기
        changName = new StringBuilder(name).append((char) ((int) (Math.random() * 26) + 65))
            .toString();
        boolean reCheck = false;

        for (UserSession userSession : room.getParticipants()) {
          if (userSession.getName().equals(changName))//아쉽지만 바뀐 이름도 이미 존재 다시 섞자
          {
            reCheck = true;
          }
        }
        existCheck = reCheck;
        if (retryNum++ > 30) {
          break;
        }
      }
      name = changName;
      //새로운 이름을 할당해서 전달해줌
      JsonObject sender = new JsonObject();
      sender.addProperty("id", "changeName");
      sender.addProperty("name", name);
      synchronized (session) {
        session.getBasicRemote().sendText(sender.toString());
      }

    }

    UserSession user = room.join(name, session);

    registry.register(user);


  }

  private void leaveRoom(UserSession user) throws IOException {
    final Room room = roomManager.getRoom(user.getRoomName());
    room.leave(user);
    if (room.getParticipants().isEmpty()) {
      roomManager.removeRoom(room);
    }
  }
}
