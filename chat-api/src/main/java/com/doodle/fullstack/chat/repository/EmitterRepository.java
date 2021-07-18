package com.doodle.fullstack.chat.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Optional;

public interface EmitterRepository {

    void addOrReplaceEmitter(String roomId, SseEmitter emitter);

    void remove(String roomId);

    Optional<List<SseEmitter>> get(String roomId);
}
