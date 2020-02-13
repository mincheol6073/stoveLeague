package com.ssafy.edu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.ChatMessage;
import com.ssafy.edu.model.KeywordValue;
import com.ssafy.edu.model.TfKeyword;
import com.ssafy.edu.model.UserList;
import com.ssafy.edu.model.response.BaseResponse;
import com.ssafy.edu.model.response.UserKeywordValue;
import com.ssafy.edu.repo.MessageRepo;
import com.ssafy.edu.repo.UserListRepo;
import com.ssafy.edu.repo.UserRepo;

import javassist.compiler.ast.Keyword;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/msg")
@CrossOrigin(origins = "*")
public class MessageController {

	@Autowired
	MessageRepo messageRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserListRepo userListRepo;

	@GetMapping("/findAllMsgByRoomId/{roomId}")
	public BaseResponse<List<ChatMessage>> findAllMsgByRoomId(@PathVariable String roomId) {

		BaseResponse<List<ChatMessage>> allRoomMsgResponse = new BaseResponse<>();

		List<ChatMessage> msgList = messageRepo.findAllByRoomId(roomId);

		allRoomMsgResponse.setResultCode(200);
		allRoomMsgResponse.setResultMessage("Success");
		allRoomMsgResponse.setResultObject(msgList);

		return allRoomMsgResponse;
	}

	@GetMapping("/findAllUserKeyword/{roomId}")
	public BaseResponse<List<UserKeywordValue>> AllUserKeywordResponse(@PathVariable String roomId) {

		List<UserKeywordValue> userKeywordValueList = new ArrayList<>();

		TfKeyword tfk = new TfKeyword();
		tfk.init();
		List<ChatMessage> msgList = messageRepo.findAllByRoomId(roomId);

		for (ChatMessage msg : msgList) {
			tfk.addTf(msg.getContent());
		}

		List<KeywordValue> wordList = tfk.getTfRank(6); // Top6

		List<UserList> userLists = userListRepo.findAllByRoomId(roomId);

		for (UserList userList : userLists) { // user마다 한번씩 다
			UserKeywordValue userKeywordValue = new UserKeywordValue();

			userKeywordValue.setUserId(userList.getUserId()); // id 설정

			tfk.init();
			List<ChatMessage> userChat = messageRepo.findAllByRoomIdAndUserId(roomId, userList.getUserId()); // user별 msg

			for (ChatMessage userMsg : userChat) {
				tfk.addTf(userMsg.getContent());
			}

			Map<String, Integer> hashMap = tfk.getHashMap(); // user별 msg tfk넣어서 hashmap만듬

			List<KeywordValue> returnKeyList = new ArrayList<>();

			for (KeywordValue keyValue : wordList) {
				KeywordValue returnKey = new KeywordValue();
				returnKey.setKeyword(keyValue.getKeyword());
				if (hashMap.containsKey(keyValue.getKeyword())) {
					returnKey.setValue(hashMap.get(keyValue.getKeyword()));
				}
				returnKeyList.add(returnKey);
			}

			userKeywordValue.setKeyList(returnKeyList);
			userKeywordValueList.add(userKeywordValue);
		}
		BaseResponse<List<UserKeywordValue>> userKeywordResponse = new BaseResponse<>();
		userKeywordResponse.setResultCode(200);
		userKeywordResponse.setResultMessage("Success");
		userKeywordResponse.setResultObject(userKeywordValueList);

		return userKeywordResponse;
	}

	@GetMapping("/findRoomKeyword/{roomId}")
	public BaseResponse<List<KeywordValue>> findRoomKeyword(@PathVariable String roomId) {

		TfKeyword tfk = new TfKeyword();
		tfk.init();

		List<ChatMessage> msgList = messageRepo.findAllByRoomId(roomId);

		for (ChatMessage msg : msgList) {
			tfk.addTf(msg.getContent());
		}

		List<KeywordValue> wordList = tfk.getTfRank(6); // Top6
		
		BaseResponse<List<KeywordValue>> roomKeywordResponse = new BaseResponse<>();
		roomKeywordResponse.setResultCode(200);
		roomKeywordResponse.setResultMessage("Success");
		roomKeywordResponse.setResultObject(wordList);

		return roomKeywordResponse;
	}

}
