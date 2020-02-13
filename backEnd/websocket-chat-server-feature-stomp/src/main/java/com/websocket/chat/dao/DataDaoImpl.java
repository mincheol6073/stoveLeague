package com.websocket.chat.dao;

import com.websocket.chat.model.User;
import com.websocket.chat.model.UserList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataDaoImpl {
    String ns = "room.";
    @Autowired
    private SqlSession sqlSession;

    public List<UserList> getUlByRoomId(String roomId) {
        return null;
    }

    public List<User> getUserList() {
        return sqlSession.selectList(ns + "getUserList");
    }
}
