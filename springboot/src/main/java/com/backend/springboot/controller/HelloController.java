package com.backend.springboot.controller;

import com.backend.springboot.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        this.brokerMessagingTemplate.convertAndSend("/topic/hi", Notification.builder().message("EVO IZ CONTROLLERA").build());//ili notification

        return new ResponseEntity<>("Yes hello this is nvtkits", HttpStatus.OK);
    }


}
