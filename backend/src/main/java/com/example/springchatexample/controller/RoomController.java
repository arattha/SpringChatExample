package com.example.springchatexample.controller;

import com.example.springchatexample.dao.ChatRepository;
import com.example.springchatexample.dto.RoomDto;
import com.sun.deploy.net.HttpResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/chat")
@CrossOrigin("*")
@Slf4j
public class RoomController {

    private final ChatRepository repository;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public List<RoomDto> rooms(){

        log.info("# All Chat Rooms");

        return repository.findAllRooms();
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public ResponseEntity<RoomDto> create(@RequestBody String name){
        log.info("# Create Chat Room , name: " + name);
        if(name == null){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(repository.createChatRoomDTO(name), HttpStatus.OK);
        //return "redirect:/chat/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public RoomDto getRoom(String roomId){

        log.info("# get Chat Room, roomID : " + roomId);

        //model.addAttribute("room", repository.findRoomById(roomId));
        return repository.findRoomById(roomId);
    }
}
