package com.example.chocokcake.controller;

import com.example.chocokcake.controller.dto.CandleListResponse;
import com.example.chocokcake.service.CandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CandleController {
    private final CandleService candleService;
    @GetMapping("/cake/{cake_id}/candle")
    public CandleListResponse getCandleList(@PathVariable("cake_id") Long id){
        return candleService.getCandleList(id);
    }
}
