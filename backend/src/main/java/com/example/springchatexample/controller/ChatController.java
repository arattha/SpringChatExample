package com.example.springchatexample.controller;

import com.example.springchatexample.dto.ChatDto;
import com.example.springchatexample.dto.SocketVO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
public class ChatController {

//    @MessageMapping(value = "/receive")
//    @SendTo("/send")
//    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
//    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
//    public SocketVO SocketHandler(SocketVO socketVO) {
//        // vo에서 getter로 userName을 가져옵니다.
//        String userName = socketVO.getUserName();
//        // vo에서 setter로 content를 가져옵니다.
//        String content = socketVO.getContent();
//
//        // 생성자로 반환값을 생성합니다.
//        SocketVO result = new SocketVO(userName, content);
//        // 반환
//        return result;
//    }
    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatDto message){
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatDto message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
