package com.doodle.fullstack.chat.service;

import com.doodle.fullstack.chat.model.EventDto;
import com.doodle.fullstack.chat.repository.EmitterRepository;
import com.doodle.fullstack.chat.wrapper.EventMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class NotificationService implements INotification {

    private final EmitterRepository emitterRepository;
    private final EventMapper eventMapper;

    @Override
    public void sendNotification(String roomID, EventDto event) {
        if (event == null) {
            log.debug("No server event to send to device.");
            return;
        }
        doSendNotification(roomID, event);
        log.info("Done sending event.");
    }

    private void doSendNotification(String roomID, EventDto event) {
        Optional<List<SseEmitter>> sseEmitters = emitterRepository.get(roomID);

        if (!sseEmitters.isPresent()) {
            log.info("No emitter for member {}", roomID);
        } else {
                try {
                    for (SseEmitter emitter : sseEmitters.get()) {
                        log.info("Sending event: {} for member: {} emitter: {}", event, roomID, emitter);
                        emitter.send(eventMapper.toSseEventBuilder(event));
                        //sseEmitters.get().remove(emitter);
                    }
                    emitterRepository.remove(roomID);
                } catch (IOException | IllegalStateException e) {
                    log.info("Error while sending event: {} for member: {} - exception: {}", event, roomID, e);
                    emitterRepository.remove(roomID);
                }
            }
        };

}
