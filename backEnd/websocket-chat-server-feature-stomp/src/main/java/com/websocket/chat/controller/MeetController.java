package com.websocket.chat.controller;

import com.websocket.chat.model.User;
import com.websocket.chat.model.UserList;
import com.websocket.chat.service.IMeetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/meeting")

public class MeetController {

    @Autowired
    private IMeetService meetService;
//    public void analyMeet(@RequestBody Meet meet) {
    @GetMapping("/getULByRoomId/{roomId}")
    public List<UserList> getULByRoomId(@PathVariable String roomId) {
        List<UserList> users = meetService.getULByRoomId(roomId);
        return users;
    }

    @GetMapping("/getUserList")
    public List<User> getUserList() {
        System.out.println("들어오나?");
        List<User> users = meetService.getUserList();
        return users;
    }

}
