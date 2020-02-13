package com.ssafy.edu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@AllArgsConstructor
@Data
@Entity
@Table(name = "user_list")
public class UserList {
    @Id
    private String userId;
    private String userName;
}