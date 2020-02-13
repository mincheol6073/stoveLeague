package com.ssafy.edu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.ChatRoom;
import com.ssafy.edu.model.UserList;
import com.ssafy.edu.model.request.RoomCreateRequest;
import com.ssafy.edu.model.response.BaseResponse;
import com.ssafy.edu.repo.ChatRoomRepo;
import com.ssafy.edu.repo.UserListRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatRoomController {
	
	@Autowired
	UserListRepo userListRepo;
	
	@Autowired
	ChatRoomRepo chatRoomRepo;
	
	@PostMapping("/createRoom")
    @ResponseBody
    public BaseResponse<ChatRoom> createRoom(RoomCreateRequest roomCreateRequest) {
		// roomName, hostId
		
		ChatRoom chatRoom = ChatRoom.create(roomCreateRequest.getRoomName());
		String roomId = chatRoom.getRoomId();
		chatRoomRepo.save(chatRoom);
		
		List<String> guestUsers = roomCreateRequest.getGuestId();

		//		String role, String roomId, String userId
		userListRepo.save(new UserList("HOST",roomId,roomCreateRequest.getHostId()));
		
		for (String guestId : guestUsers) {
			UserList userList = new UserList("GUEST", roomId, guestId);
			userListRepo.save(userList);
		}
		
        log.info("Room {}",chatRoom);
        BaseResponse<ChatRoom> createResponse = new BaseResponse<>();
        createResponse.setResultCode(200);
        createResponse.setResultMessage("Success");
        createResponse.setResultObject(chatRoom);
        // db에 추가 필요
        return createResponse;
    }
}
