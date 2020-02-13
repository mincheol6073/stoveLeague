package com.ssafy.edu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.ChatRoom;

@Repository
public interface ChatRoomRepo extends JpaRepository<ChatRoom, String>{

	ChatRoom findOneByRoomId(String roomId);

}
