package com.doodle.fullstack.chat.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class User {

    @Id
    public String id;

    public User(String username) {
        this.username = username;
    }

    @Getter
    @Setter
    public String username;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
