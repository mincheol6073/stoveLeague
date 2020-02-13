package com.websocket.chat.controller;

import com.websocket.chat.model.Meet;
import com.websocket.chat.service.IMeetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api/meeting")

public class MeetController {

    @Autowired
    private IMeetService meetService;

    @GetMapping("/analyMeet")
    public void analyMeet(@PathVariable Meet meet) {
        
        meet.toString();

        return;
    }

}
