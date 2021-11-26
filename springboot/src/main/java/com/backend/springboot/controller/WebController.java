package com.backend.springboot.controller;

import com.backend.springboot.model.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {

    @MessageMapping("/hello")
    @SendTo("/topic/hi")
    public Notification greeting(String msg) throws Exception {
        return Notification.builder()
                .message("Test message " + msg)
                .build();
    }
}
