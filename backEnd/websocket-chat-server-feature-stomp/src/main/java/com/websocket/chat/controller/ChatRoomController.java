package com.websocket.chat.controller;

import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

        private final ChatRoomRepository chatRoomRepository;

        @GetMapping("/room")
        public String rooms(Model model) {
            return "/chat/room";
        }

        @GetMapping("/rooms")
        @ResponseBody
        public List<ChatRoom> room() {
            return chatRoomRepository.findAllRoom();
        }

        @PostMapping("/createRoom")
        @ResponseBody
        public ChatRoom createRoom(@RequestParam String name) {
            ChatRoom chatRoom = chatRoomRepository.createChatRoom(name);
            log.info("Room {}",chatRoom);
            return chatRoom;
        }

        @GetMapping("/room/enter/{roomId}")
        public String roomDetail(Model model, @PathVariable String roomId) {
            model.addAttribute("roomId", roomId);
            return "/chat/roomdetail";
        }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
