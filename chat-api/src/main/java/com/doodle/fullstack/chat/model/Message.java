package com.doodle.fullstack.chat.model;

import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Message {
    @Id
    public String id;

    public String roomName;

    public String userName;

    public String message;

    @Setter
    public Date date;

    public Message(String roomName, String userName, String message) {
        this.roomName = roomName;
        this.userName = userName;
        this.message = message;
    }
}
