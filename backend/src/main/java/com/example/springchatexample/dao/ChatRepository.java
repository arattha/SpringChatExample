package com.example.springchatexample.dao;

import com.example.springchatexample.dto.RoomDto;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRepository {
    private Map<String, RoomDto> chatRoomDTOMap;

    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    public List<RoomDto> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<RoomDto> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    public RoomDto findRoomById(String id){
        return chatRoomDTOMap.get(id);
    }

    public RoomDto createChatRoomDTO(String name){
        RoomDto room = RoomDto.create(name);
        chatRoomDTOMap.put(room.getRoomId(), room);

        return room;
    }
}
