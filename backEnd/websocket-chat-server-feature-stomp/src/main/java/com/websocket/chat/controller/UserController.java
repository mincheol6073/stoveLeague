package com.websocket.chat.controller;

import com.websocket.chat.model.User;
import com.websocket.chat.model.response.BaseResponse;
import com.websocket.chat.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

//    @Autowired
    private UserRepo userRepo;

//    private final User

    @PostMapping("/join")
    @ResponseBody
    public BaseResponse<String> join(User user){
        BaseResponse<String> joinResponse = new BaseResponse<>();
        userRepo.save(user);

        joinResponse.setResultCode(204);
        joinResponse.setResultMessage("Success");
        joinResponse.setResultObject("No Content");
        return joinResponse;
    }
}
