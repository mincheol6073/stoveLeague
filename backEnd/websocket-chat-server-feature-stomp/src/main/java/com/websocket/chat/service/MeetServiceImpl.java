package com.websocket.chat.service;

import com.websocket.chat.dao.DataDaoImpl;
import com.websocket.chat.model.User;
import com.websocket.chat.model.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeetServiceImpl implements IMeetService{

    @Autowired
    private DataDaoImpl dataDaoImpl;

    @Override
    @Transactional(readOnly=true)
    public List<UserList> getULByRoomId(String roomId) {
        return null;
//        return dataDaoImpl.getUlByRoomId(roomId);
    }

    @Override
    @Transactional(readOnly=true)
    public List<User> getUserList() {
        return dataDaoImpl.getUserList();
    }
}
