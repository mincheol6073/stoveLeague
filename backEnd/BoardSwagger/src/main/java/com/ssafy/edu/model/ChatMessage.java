package com.ssafy.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "message")
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private int messageId;
	@Column(name = "room_id")
	private String roomId; // 방번호
	@Column(name = "user_id")
	private String userId;
    private String type; // 메시지 타입
    private String time;
    private String content; // 메시지
    private Double score;
    private Double magnitude;

}