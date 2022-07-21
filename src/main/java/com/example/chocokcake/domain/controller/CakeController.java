package com.example.chocokcake.domain.controller;

import com.example.chocokcake.domain.controller.dto.response.MessageResponse;
import com.example.chocokcake.domain.controller.dto.response.ReadCakeDetailsResponse;
import com.example.chocokcake.domain.controller.dto.response.ReadUserCakeListResponse;
import com.example.chocokcake.domain.controller.dto.request.ThemeRequest;
import com.example.chocokcake.domain.service.CakeService;
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
    public ReadUserCakeListResponse readMyCake() {
        return cakeService.readMyCake();
    }

    @GetMapping("/{cake_id}")
    public ReadCakeDetailsResponse readCakeDetails(@PathVariable("cake_id") Long cakeId) {
        return cakeService.readCakeDetails(cakeId);
    }
}
