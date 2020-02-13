package com.ssafy.edu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.ChatRoom;
import com.ssafy.edu.model.response.BaseResponse;

@RestController
@RequestMapping("/chat")
public class ChatRoomController {
	
//	 @GetMapping("/findAllRooms")
//     @ResponseBody
//     public BaseResponse<List<ChatRoom>> room() {
//         List<ChatRoom> roomList = chatRoomRepository.findAllRoom();
//         BaseResponse<List<ChatRoom>> findAllRoomResponse = new BaseResponse<>();
//         findAllRoomResponse.setResultCode(200);
//         findAllRoomResponse.setResultMessage("Success");
//         findAllRoomResponse.setResultObject(roomList);
//         return findAllRoomResponse;
//     }
}
