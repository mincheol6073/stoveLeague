package com.websocket.chat.controller;

import com.websocket.chat.model.response.BaseResponse;
import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
//@Controller
@RestController
@RequestMapping("/chat")
public class ChatRoomController {

        private final ChatRoomRepository chatRoomRepository;

//        @GetMapping("/room")
//        public String rooms(Model model) {
//            return "/chat/room";
//        }

        @GetMapping("/findAllRooms")
        @ResponseBody
        public BaseResponse<List<ChatRoom>> room() {
            List<ChatRoom> roomList = chatRoomRepository.findAllRoom();
            BaseResponse<List<ChatRoom>> findAllRoomResponse = new BaseResponse<>();
            findAllRoomResponse.setResultCode(200);
            findAllRoomResponse.setResultMessage("Success");
            findAllRoomResponse.setResultObject(roomList);
            return findAllRoomResponse;
        }

        @PostMapping("/createRoom")
        @ResponseBody
        public BaseResponse<ChatRoom> createRoom(@RequestParam ChatRoom createRequest) {
            ChatRoom chatRoom = chatRoomRepository.createChatRoom(createRequest);
            log.info("Room {}",chatRoom);
            BaseResponse<ChatRoom> createResponse = new BaseResponse<>();
            createResponse.setResultCode(200);
            createResponse.setResultMessage("Success");
            createResponse.setResultObject(chatRoom);
            // db에 추가 필요
            return createResponse;
        }

        @GetMapping("/enterRoom/{roomId}")
        public BaseResponse<String> enterRoom(@PathVariable String roomId) {
//            model.addAttribute("roomId", roomId);
            BaseResponse<String> enterResponse = new BaseResponse<>();
            enterResponse.setResultCode(204);
            enterResponse.setResultMessage("Success");
            enterResponse.setResultObject("No Content");
            return enterResponse;
        }

        @GetMapping("/room/{roomId}")
        @ResponseBody
        public ChatRoom roomInfo(@PathVariable String roomId) {
            return chatRoomRepository.findRoomById(roomId);
        }
}
