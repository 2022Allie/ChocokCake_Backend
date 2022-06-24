package com.example.chocokcake.controller;

import com.example.chocokcake.dto.MessageResponse;
import com.example.chocokcake.dto.UserRequest;
import com.example.chocokcake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/account")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createUser(@RequestBody UserRequest request){
        return userService.join(request);
    }
}
