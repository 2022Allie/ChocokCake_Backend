package com.example.chocokcake.controller;

import com.example.chocokcake.controller.dto.MessageResponse;
import com.example.chocokcake.controller.dto.ThemeRequest;
import com.example.chocokcake.service.CakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CakeController {
    private final CakeService cakeService;
    @PostMapping("/cake")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createCake(@RequestBody ThemeRequest theme){
        return cakeService.createCake(theme);
    }
}
