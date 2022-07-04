package com.example.chocokcake.controller;

import com.example.chocokcake.controller.dto.MessageResponse;
import com.example.chocokcake.controller.dto.ReadCakeDetailsResponse;
import com.example.chocokcake.controller.dto.ReadUserCakeResponse;
import com.example.chocokcake.controller.dto.ThemeRequest;
import com.example.chocokcake.service.CakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cake")
public class CakeController {
    private final CakeService cakeService;
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageResponse createCake(@RequestBody ThemeRequest theme){
        return cakeService.createCake(theme);
    }

    @GetMapping("/mine")
    public ReadUserCakeResponse readMyCake() {
        return cakeService.readMyCake();
    }

    @GetMapping("/{cake_id}")
    public ReadCakeDetailsResponse readCakeDetails(@PathVariable("cake_id") Long cakeId) {
        return cakeService.readCakeDetails(cakeId);
    }
}
