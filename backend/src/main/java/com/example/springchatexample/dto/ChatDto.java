package com.example.springchatexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDto {
    private String roomId;
    private String writer;
    private String message;
}
