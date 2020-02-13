package com.websocket.chat.service;

import com.websocket.chat.model.User;
import com.websocket.chat.model.UserList;

import java.util.List;

public interface IMeetService {
    List<UserList> getULByRoomId(String roomId);
    List<User> getUserList();
}
