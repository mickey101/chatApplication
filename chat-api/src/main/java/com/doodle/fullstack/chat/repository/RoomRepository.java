package com.doodle.fullstack.chat.repository;

import com.doodle.fullstack.chat.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {

    Room findByRoomName(String roomName);

}
