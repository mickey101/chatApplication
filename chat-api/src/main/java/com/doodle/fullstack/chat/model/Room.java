package com.doodle.fullstack.chat.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class Room {

    @Id
    public String id;

    public Room(String roomName) {
        this.roomName = roomName;
    }

    @Getter
    @Setter
    public String roomName;
}
