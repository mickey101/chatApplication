package com.doodle.fullstack.chat.service;

import com.doodle.fullstack.chat.model.Room;
import com.doodle.fullstack.chat.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public String createRoom(String roomName) {

        Room room = roomRepository.findByRoomName(roomName);

        if (room != null) {
            return "Invalid_RoomName";
        }
        Room newRoom = new Room(roomName);

        roomRepository.save(newRoom);
        return newRoom.getRoomName();
    }
}
