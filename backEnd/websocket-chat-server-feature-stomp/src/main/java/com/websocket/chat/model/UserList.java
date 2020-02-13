package com.websocket.chat.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_list")
public class UserList {
    @Id
    private String userId;
    private String userName;
}
