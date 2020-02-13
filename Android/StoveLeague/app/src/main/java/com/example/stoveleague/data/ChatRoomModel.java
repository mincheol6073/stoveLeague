package com.example.stoveleague.data;

public class ChatRoomModel {
    private String name;
    private String roomId;

    public ChatRoomModel(String name, String roomId) {
        this.name = name;
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
