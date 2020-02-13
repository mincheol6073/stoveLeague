package com.ssafy.edu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.ChatMessage;

@Repository
public interface MessageRepo extends JpaRepository<ChatMessage, Integer>{

	List<ChatMessage> findAllByRoomId(String roomId);

	List<ChatMessage> findAllByRoomIdAndUserId(String roomId, String userId);

}
