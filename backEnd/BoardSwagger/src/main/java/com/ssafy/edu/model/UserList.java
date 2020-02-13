package com.ssafy.edu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_list")
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_list_id")
    private int userListId;
    private String role;
    @Column(name = "room_id")
    private String roomId;
    @Column(name = "user_id")
    private String userId;
    
	public UserList(String role, String roomId, String userId) {
		super();
		this.role = role;
		this.roomId = roomId;
		this.userId = userId;
	}
}