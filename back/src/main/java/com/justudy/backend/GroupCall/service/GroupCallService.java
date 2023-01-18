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
import org.kurento.client.IceCandidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 
 * @author Ivan Gracia (izanmail@gmail.com)
 * @since 4.3.1
 */
@Service
@ServerEndpoint(value ="/groupcall", configurator = ServerEndpointConfig.class)
public class GroupCallService   {

  private static final Logger log = LoggerFactory.getLogger(GroupCallService.class);

  private static final Gson gson = new GsonBuilder().create();


  @Autowired
  private RoomManager roomManager;

  @Autowired
  private UserRegistry registry;

  @OnMessage
  public void OnMessage(Session session, String message) throws Exception {
    final JsonObject jsonMessage = gson.fromJson(message, JsonObject.class);

    final UserSession user = registry.getBySession(session);

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
        final String personName = jsonMessage.get("name").getAsString();
        final String roomName = jsonMessage.get("room").getAsString();
        ban(personName,roomName);
        break;
      case "mute":
        final String personName2 = jsonMessage.get("name").getAsString();
        final String roomName2 = jsonMessage.get("room").getAsString();
        mute(personName2,roomName2);
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

  private void mute(String personName, String roomName)throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for(UserSession user : room.getParticipants()){
      user.transferMute(personName);
    }
  }

  private void ban(String personName, String roomName) throws Exception {
    final Room room = roomManager.getRoom(roomName);

    for(UserSession user : room.getParticipants()){
      user.transferBan(personName);
    }
  }


  @OnOpen
  public void afterConnectionEstablished() throws Exception {
    log.info("afterConnectionEstablished...........................");

  }

  @OnClose
  public void afterConnectionClosed(Session session) throws Exception {
    UserSession user = registry.removeBySession(session);
    roomManager.getRoom(user.getRoomName()).leave(user);
  }

  private void joinRoom(JsonObject params, Session session) throws IOException {
    final String roomName = params.get("room").getAsString();
    final String name = params.get("name").getAsString();
    log.info("PARTICIPANT {}: trying to join room {}", name, roomName);

    Room room = roomManager.getRoom(roomName);
    final UserSession user = room.join(name, session);
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
