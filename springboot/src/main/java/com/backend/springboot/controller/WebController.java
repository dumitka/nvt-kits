package com.backend.springboot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {

    @MessageMapping("/hello") //we can send objects to /nvt/hello
    @SendTo("/topic/hi")
    public String greeting(String name) throws Exception {
        return "Hi! " + name + "!";
    }
}
