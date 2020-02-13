package com.websocket.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserList {
    String roomId;
    String role;
    String user_id;
    String user_name;
}
