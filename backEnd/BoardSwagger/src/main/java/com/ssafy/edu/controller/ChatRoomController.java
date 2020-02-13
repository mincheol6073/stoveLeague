package com.ssafy.edu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.ChatRoom;
import com.ssafy.edu.model.User;
import com.ssafy.edu.model.UserList;
import com.ssafy.edu.model.request.RoomCreateRequest;
import com.ssafy.edu.model.response.BaseResponse;
import com.ssafy.edu.repo.ChatRoomRepo;
import com.ssafy.edu.repo.UserListRepo;
import com.ssafy.edu.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatRoomController {
	
	@Autowired
	UserListRepo userListRepo;
	
	@Autowired
	ChatRoomRepo chatRoomRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/createRoom")
    public BaseResponse<ChatRoom> createRoom(@RequestBody RoomCreateRequest roomCreateRequest) {
		// roomName, hostId
		
		log.info("***chat/createRoom*** {}",new Date());
		ChatRoom chatRoom = ChatRoom.create(roomCreateRequest.getRoomName());
		String roomId = chatRoom.getRoomId();
		chatRoomRepo.save(chatRoom);
		
		List<String> guestUsers = roomCreateRequest.getGuestId();

		userListRepo.save(new UserList("HOST",roomId,roomCreateRequest.getHostId()));
		
		for (String guestId : guestUsers) {
			UserList userList = new UserList("GUEST", roomId, guestId);
			userListRepo.save(userList);
		}
		
		BaseResponse<ChatRoom> createRoomResponse = new BaseResponse<>();
		createRoomResponse.setResultCode(200);
		createRoomResponse.setResultMessage("Success");
		createRoomResponse.setResultObject(chatRoom);
		
        return createRoomResponse;
    }
	
	@GetMapping("/findAllUsersInRoom/{roomId}")
    public BaseResponse<List<User>> findAllUsersInRoom(@PathVariable String roomId){
        log.info("***chat/findAllUsersInRoom*** {}",new Date());

        List<UserList> userLists = userListRepo.findAllByRoomId(roomId);
        
        List<User> users = new ArrayList<>();
        
        for (UserList list : userLists) {
			users.add(userRepo.findOneByUserId(list.getUserId()));
		}
        
        BaseResponse<List<User>> userInRoomResponse = new BaseResponse<>();
        userInRoomResponse.setResultCode(200);
        userInRoomResponse.setResultMessage("Success");
        userInRoomResponse.setResultObject(users);
        
        return userInRoomResponse;
    }
	
	@GetMapping("/findAllRoomByUser/{userId}")
	public BaseResponse<List<ChatRoom>> findAllRoomByUser(@PathVariable String userId){
		
		List<UserList> roomIdList = userListRepo.findAllByUserId(userId); 
		List<ChatRoom> roomList = new ArrayList<>();
		
		for (UserList userList : roomIdList) {
			String roomId = userList.getRoomId();
			roomList.add(chatRoomRepo.findOneByRoomId(roomId));
		}
		BaseResponse<List<ChatRoom>> roomListResponse = new BaseResponse<>();
		roomListResponse.setResultCode(200);
		roomListResponse.setResultMessage("Success");
		roomListResponse.setResultObject(roomList);
		
		return roomListResponse;
	}
}
