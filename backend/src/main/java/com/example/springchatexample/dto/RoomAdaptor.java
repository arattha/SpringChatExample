package com.example.springchatexample.dto;

public class RoomAdaptor {

    public static RoomDto entityToDto(Room room){
        return RoomDto.builder()
                .roomId(room.getRoomId())
                .name(room.getName())
                .build();
    }

    public static Room dtoToEntity(RoomDto roomDto){
        return Room.builder()
                .roomId(roomDto.getRoomId())
                .name(roomDto.getName())
                .build();
    }

}
