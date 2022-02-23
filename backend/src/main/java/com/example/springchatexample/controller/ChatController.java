package com.example.springchatexample.controller;

import com.example.springchatexample.dto.ChatDto;
import com.example.springchatexample.dto.SocketVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatDto message){

        log.info("# Enter Chat Room , name: " + message.getRoomId() + " " + message.getWriter());
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        message.setWriter("");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatDto message){
        log.info("# send Chat Room , name: " + message.getRoomId() + " " + message.getWriter() + " " + message.getMessage());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
