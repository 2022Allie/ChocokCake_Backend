package com.example.chocokcake.domain.controller;

import com.example.chocokcake.domain.controller.dto.response.CandleListResponse;
import com.example.chocokcake.domain.controller.dto.request.LetterRequest;
import com.example.chocokcake.domain.controller.dto.response.LetterResponse;
import com.example.chocokcake.domain.controller.dto.response.MessageResponse;
import com.example.chocokcake.domain.service.CandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cake")
public class CandleController {
    private final CandleService candleService;
    @GetMapping("/{cake_id}/candle")
    public CandleListResponse getCandleList(@PathVariable("cake_id") Long id){
        return candleService.getCandleList(id);
    }

    @PostMapping("/{cake_id}/candle")
    public MessageResponse postLetter(@PathVariable("cake_id") Long id, @RequestBody LetterRequest request) {
        return candleService.postLetter(id, request);
    }

    @GetMapping("/candle/{candle_id}")
    public LetterResponse getCandle(@PathVariable("candle_id") Long candleId) {
        return candleService.getCandle(candleId);
    }
}
