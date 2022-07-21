package com.example.chocokcake.domain.controller;

import com.example.chocokcake.domain.controller.dto.*;
import com.example.chocokcake.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {
    private final UserService userService;

    @PostMapping("/id")
    public MessageResponse id(@RequestBody AccountIdRequest request) {
        return userService.checkIdDuplication(request);
    }
    @GetMapping("/information")
    public UserInfoResponse getUserInfo(){
        return userService.getUserInfo();
    }
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createUser(@RequestBody UserRequest request){
        return userService.join(request);
    }
    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request){
        return userService.login(request);
    }
    @DeleteMapping
    public MessageResponse withdrawal(@RequestBody LoginRequest request){
        return userService.withdrawal(request);
    }
}
