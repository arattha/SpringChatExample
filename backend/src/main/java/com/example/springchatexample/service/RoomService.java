package com.example.springchatexample.service;

import com.example.springchatexample.dao.RoomDao;
import com.example.springchatexample.dto.Room;
import com.example.springchatexample.dto.RoomAdaptor;
import com.example.springchatexample.dto.RoomDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {

    RoomDao roomDao;

    public List<RoomDto> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        //List<RoomDto> result = new ArrayList<>(chatRoomDTOMap.values());
        List<Room> list = roomDao.findAll();
        Collections.reverse(list);

        List<RoomDto> result = new ArrayList<>();
        list.forEach(v -> result.add(RoomAdaptor.entityToDto(v)));

        return result;
    }

    public RoomDto findRoomById(String id){
        Optional<Room> room = roomDao.findById(id);
        if(!room.isPresent()){
            return null;
        }
        return RoomAdaptor.entityToDto(room.get());
    }

    @Transactional
    public void createChatRoom(String name){
        RoomDto room = RoomDto.create(name);
        roomDao.save(RoomAdaptor.dtoToEntity(room));
    }
}