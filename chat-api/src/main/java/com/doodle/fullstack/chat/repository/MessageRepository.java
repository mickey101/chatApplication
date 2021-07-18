package com.doodle.fullstack.chat.repository;

import com.doodle.fullstack.chat.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findAllByRoomNameOrderByDateAsc(String roomName);

}
