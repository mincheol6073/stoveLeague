package com.websocket.chat.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Data
public class TXT {
    private String type;
    private String userId;
    private String userName;
    private String time;
    private String message;
}
