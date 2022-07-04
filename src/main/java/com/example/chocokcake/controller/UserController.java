package com.example.chocokcake.controller;

import com.example.chocokcake.controller.dto.LoginRequest;
import com.example.chocokcake.controller.dto.MessageResponse;
import com.example.chocokcake.controller.dto.TokenResponse;
import com.example.chocokcake.controller.dto.UserRequest;
import com.example.chocokcake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {
    private final UserService userService;
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
