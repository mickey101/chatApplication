package com.doodle.fullstack.chat.controller;

import com.doodle.fullstack.chat.model.EventDto;
import com.doodle.fullstack.chat.model.Message;
import com.doodle.fullstack.chat.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class ChatController {

    public static final String ROOM_ID = "RoomId";

    private final UserService userService;

    private final RoomService roomService;

    private final MessageService messageService;

    private final EmitterService emitterService;

    private final NotificationService notificationService;

    public ChatController(UserService userService, RoomService roomService, MessageService messageService, EmitterService emitterService, NotificationService notificationService ) {
        this.userService = userService;
        this.roomService = roomService;
        this.messageService = messageService;
        this.emitterService = emitterService;
        this.notificationService = notificationService;
    }

    @GetMapping("/events/{roomId}")
    public SseEmitter subscribeToEvents(@PathVariable String roomId) {
        return emitterService.createEmitter(roomId);
    }

    @PostMapping("/createUser/{userName}")
    ResponseEntity<String> createUser(@PathVariable String userName) {

        String createdUser = userService.createUser(userName);

        if (createdUser.equals("Invalid_Username")) {
            return new ResponseEntity<>(createdUser,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/createRoom/{roomName}")
    ResponseEntity<String> createRoom(@PathVariable String roomName) {

        String createdRoom = roomService.createRoom(roomName);

        if (createdRoom.equals("Invalid_RoomName")) {
            return new ResponseEntity<>(createdRoom,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdRoom, HttpStatus.OK);
    }


    @GetMapping("/rooms/{roomName}")
    ResponseEntity<List<Message>> getMessages(@PathVariable String roomName) {

        List<Message> messages = messageService.fetchMessages(roomName);

        return new ResponseEntity<>(messages,HttpStatus.OK);
    }


    @PostMapping("/rooms/message")
    ResponseEntity<Message> postMessage(@RequestBody Message message) {
        Message postedMessage = messageService.postMessage(message.roomName, message.userName, message.message);
        EventDto eventDto = new EventDto();
        HashMap<String,String> map = new HashMap<>();
        map.put("status", "SUCCESS");
        eventDto.setType("update");
        eventDto.setBody(map);
        log.info("It got called with an update!");
        notificationService.sendNotification(message.roomName, eventDto);
        return new ResponseEntity<>(postedMessage, HttpStatus.OK);
    }
}






