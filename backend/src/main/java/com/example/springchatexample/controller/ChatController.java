package com.example.springchatexample.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ChatController {

    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/join")
    public void joinChattingRoom(){

    }

    @MessageMapping(value = "/send")
    public void sendChat(){
        
    }

}
