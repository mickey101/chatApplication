package com.doodle.fullstack.chat.service;

import com.doodle.fullstack.chat.model.EventDto;

public interface INotification {

    void sendNotification(String roomId, EventDto event);
}
