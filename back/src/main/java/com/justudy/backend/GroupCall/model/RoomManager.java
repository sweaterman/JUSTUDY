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

import org.kurento.client.KurentoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
*
* RoomManager :  방관리 클래스
*
* 해당 클래스를 통해 방마다 유저를 나누어서 통신할 수 있다.
*
**/
public class RoomManager  {

  private final Logger log = LoggerFactory.getLogger(RoomManager.class);

  //kurento 라이브러리를 쓰기 위함
  @Autowired
  private KurentoClient kurento;

  //HashMap  +  Multi-Thread 기능이 가능한 ConcurrentHashMap를 사용함.
  private final ConcurrentMap<String, Room> rooms = new ConcurrentHashMap<>();

  //방이 없으면 방을 새로 만들고, 해당 방의 객체를 보내준다. Room 이름 정보와 MediaPipeline를 보내준다.(같은 미디어 파이프 라인끼리 N:N화상회의를 하기위함
  public Room getRoom(String roomName) {
    log.debug("Searching for room {}", roomName);
    Room room = rooms.get(roomName);

    if (room == null) {
      log.debug("Room {} not existent. Will create now!", roomName);
      room = new Room(roomName, kurento.createMediaPipeline());
      rooms.put(roomName, room);
    }
    log.debug("Room {} found!", roomName);
    return room;
  }

 // 방을 떠나면 해당 미디어 파이프라인과 방정보를 삭제해주기 위한 부분(closable 인터페이스를 받았기 때문에 auto close가 가능하다.)
  public void removeRoom(Room room) {
    this.rooms.remove(room.getName());
    room.close();
    log.info("Room {} removed and closed", room.getName());
  }

}
