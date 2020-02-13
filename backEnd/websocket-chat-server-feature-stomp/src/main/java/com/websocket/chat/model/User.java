package com.websocket.chat.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Data
public class User {
    private String userId;
    private String userName;
}
