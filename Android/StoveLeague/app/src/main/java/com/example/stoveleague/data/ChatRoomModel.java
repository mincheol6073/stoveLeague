package com.example.stoveleague.data;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatRoomModel {
    private String title;
    private String name;
    private String roomId;
    private HashMap<String,Boolean> users; //채티방의 유저들
    public HashMap<String, Comment> comments; // 채팅방의 내용

    public ChatRoomModel() {
        this.users = new HashMap<>();
    }


    public HashMap<String, Boolean> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, Boolean> users) {
        this.users = users;
    }

    public ChatRoomModel(String name, String roomId) {
        this.name = name;
        this.roomId = roomId;
        this.users = new HashMap<>();
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
    public static class Comment{
        public String uid;
        public String message;
    }
}
