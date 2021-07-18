package com.doodle.fullstack.chat.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class InMemoryEmitterRepository implements EmitterRepository {

    private final Map<String, List<SseEmitter>> userEmitterMap = new ConcurrentHashMap<>();

    @Override
    public void addOrReplaceEmitter(String roomId, SseEmitter emitter) {
        List<SseEmitter> emitters;
        if (userEmitterMap.containsKey(roomId)){
            emitters = userEmitterMap.get(roomId);
        } else {
            emitters = new ArrayList<>();
        }
        emitters.add(emitter);
        userEmitterMap.put(roomId, emitters);
    }

    @Override
    public void remove(String roomId) {
        userEmitterMap.remove(roomId);
    }

    @Override
    public Optional<List<SseEmitter>> get(String roomId) {
        return Optional.ofNullable(userEmitterMap.get(roomId));
    }
}
