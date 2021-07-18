package com.doodle.fullstack.chat.service;

import com.doodle.fullstack.chat.repository.EmitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmitterService {

    private final long eventsTimeout;
    private final EmitterRepository repository;

    public EmitterService(@Value("${events.connection.timeout}") long eventsTimeout,
                          EmitterRepository repository) {
        this.eventsTimeout = eventsTimeout;
        this.repository = repository;
    }

    public SseEmitter createEmitter(String roomId) {

        SseEmitter emitter = new SseEmitter(eventsTimeout);
        emitter.onCompletion(() -> {
            Optional<List<SseEmitter>> emitters = repository.get(roomId);
            emitters.ifPresent(sseEmitters -> sseEmitters.remove(emitter));
        });
        emitter.onTimeout(() -> {
            Optional<List<SseEmitter>> emitters = repository.get(roomId);
            emitters.ifPresent(sseEmitters -> sseEmitters.remove(emitter));
            });
        emitter.onError(e -> {
            Optional<List<SseEmitter>> emitters = repository.get(roomId);
            emitters.ifPresent(sseEmitters -> sseEmitters.remove(emitter));
        });
        repository.addOrReplaceEmitter(roomId, emitter);
        return emitter;
    }
}
