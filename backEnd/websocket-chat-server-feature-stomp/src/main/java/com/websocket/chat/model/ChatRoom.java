package com.websocket.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }
}
