package com.example.chocokcake.controller;

<<<<<<< main
import com.example.chocokcake.controller.dto.LoginRequest;
import com.example.chocokcake.controller.dto.MessageResponse;
import com.example.chocokcake.controller.dto.TokenResponse;
=======
import com.example.chocokcake.controller.dto.MessageResponse;
>>>>>>> Create Cake API
import com.example.chocokcake.controller.dto.UserRequest;
import com.example.chocokcake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/account")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createUser(@RequestBody UserRequest request){
        return userService.join(request);
    }
    @PostMapping("/account/login")
    public TokenResponse login(LoginRequest request){
        return userService.login(request);
    }
}
