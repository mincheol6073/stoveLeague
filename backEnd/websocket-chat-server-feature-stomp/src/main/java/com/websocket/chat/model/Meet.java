package com.websocket.chat.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Data
public class Meet {
    private String date;
    private int meetId;
    private String meetTitle;
    private String meetPw;
    private String leaderId;
    private List<User> userList;
    private List<TXT> txt;
}
