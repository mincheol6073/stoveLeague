package com.ssafy.edu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.edu.model.ChatMessage;
import com.ssafy.edu.model.User;
import com.ssafy.edu.model.response.BaseResponse;
import com.ssafy.edu.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepo userRepo;

//    @PostMapping("/join")
////    @ResponseBody
//    public BaseResponse join(User user){
//        BaseResponse joinResponse = new BaseResponse();
//        log.info("***user/join*** {}",new Date());
//        userRepo.save(user);
//
//        joinResponse.setResultCode(204);
//        joinResponse.setResultMessage("Success");
//        return joinResponse;
//    }
    
    @GetMapping("/findAllUsers")
    public BaseResponse<List<User>> findAllUsers(){
        log.info("***user/findAllUsers*** {}",new Date());

        List<User> userList = userRepo.findAll();
        
        BaseResponse<List<User>> allUserResponse = new BaseResponse<>();
        allUserResponse.setResultCode(200);
        allUserResponse.setResultMessage("Success");
        allUserResponse.setResultObject(userList);
        
        return allUserResponse;
    }
}
