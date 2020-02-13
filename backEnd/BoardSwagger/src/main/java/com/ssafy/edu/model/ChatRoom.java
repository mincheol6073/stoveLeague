package com.ssafy.edu.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "room")
public class ChatRoom {
    @Id
    @Column(name = "room_id")
    private String roomId;
    @Column(name = "room_name")
    private String name;
    @Column(name = "room_date")
    private String date;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        chatRoom.date = dateFormat.format(new Date());

        return chatRoom;
    }
}
