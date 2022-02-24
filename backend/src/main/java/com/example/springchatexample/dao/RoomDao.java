package com.example.springchatexample.dao;

import com.example.springchatexample.dto.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDao extends JpaRepository<Room, String> {
    List<Room> findByRoomId(String roomId);
}
