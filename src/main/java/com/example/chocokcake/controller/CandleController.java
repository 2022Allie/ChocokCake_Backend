package com.example.chocokcake.controller;

import com.example.chocokcake.controller.dto.CandleListResponse;
import com.example.chocokcake.controller.dto.LetterRequest;
import com.example.chocokcake.controller.dto.LetterResponse;
import com.example.chocokcake.controller.dto.MessageResponse;
import com.example.chocokcake.service.CandleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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

    @GetMapping("/cake/candle/{candle_id}")
    public LetterResponse getCandle(@PathVariable("candle_id") Long candleId) {
        return candleService.getCandle(candleId);
    }
}
