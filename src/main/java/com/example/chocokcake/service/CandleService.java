package com.example.chocokcake.service;

import com.example.chocokcake.controller.dto.*;
import com.example.chocokcake.entity.Cake;
import com.example.chocokcake.entity.Candle;
import com.example.chocokcake.entity.repository.CakeRepository;
import com.example.chocokcake.entity.repository.CandleRepository;
import com.example.chocokcake.entity.repository.UserRepository;
import com.example.chocokcake.exception.BaseException;
import com.example.chocokcake.exception.ErrorCode;
import com.example.chocokcake.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandleService {
    private final CakeRepository cakeRepository;
    private final AuthenticationFacade authenticationFacade;
    private final CandleRepository candleRepository;
    public CandleListResponse getCandleList(Long id) {
        if(authenticationFacade.getCurrentUser() == null) {
            throw new BaseException(ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION);
        }
        Cake cake = cakeRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_CAKE));
        return CandleListResponse.builder()
                .candles(candleRepository.findByCake(cake).stream()
                        .map(candle -> CandleResponse.builder()
                                .id(candle.getId())
                                .postman(candle.getPostman())
                                .theme(candle.getTheme())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public MessageResponse postLetter(Long id, LetterRequest request) {
        Cake cake = cakeRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_CAKE));
        candleRepository.save(Candle.builder()
                .letter(request.getLetter())
                .postman(request.getPostman())
                .theme(request.getTheme())
                .build());
        return MessageResponse.builder()
                .message("편지를 보냈어요")
                .build();
    }

    @Transactional
    public LetterResponse getCandle(Long candleId) {
        Candle candle = candleRepository.findById(candleId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_CANDLE));
        return LetterResponse.builder()
                .letter(candle.getLetter())
                .postman(candle.getPostman())
                .theme(candle.getTheme())
                .build();
    }
}
