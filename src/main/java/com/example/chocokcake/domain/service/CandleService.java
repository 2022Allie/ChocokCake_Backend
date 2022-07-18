package com.example.chocokcake.domain.service;

import com.example.chocokcake.domain.controller.dto.*;
import com.example.chocokcake.domain.entity.cake.Cake;
import com.example.chocokcake.domain.entity.candle.Candle;
import com.example.chocokcake.domain.entity.cake.CakeRepository;
import com.example.chocokcake.domain.entity.candle.CandleRepository;
import com.example.chocokcake.global.exception.costomException.BaseException;
import com.example.chocokcake.global.exception.ErrorCode;
import com.example.chocokcake.global.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
        if(authenticationFacade.getCurrentUser() != cake.getUser()){
            throw new BaseException(ErrorCode.FORBIDDEN);
        }
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
                .cake(cake)
                .build());
        return MessageResponse.builder()
                .message("편지를 보냈어요")
                .build();
    }

    @Transactional
    public LetterResponse getCandle(Long candleId) {
        Candle candle = candleRepository.findById(candleId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_CANDLE));
        Cake cake = cakeRepository.findById(candle.getCake().getId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_CAKE));
        if(!authenticationFacade.getCurrentUser().equals(cake.getUser())){
            throw new BaseException(ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION);
        }
        if(cake.getBirthDay().isAfter(LocalDate.now())){
            throw new BaseException(ErrorCode.NOT_YET_BIRTHDAY);
        }
        return LetterResponse.builder()
                .letter(candle.getLetter())
                .postman(candle.getPostman())
                .theme(candle.getTheme())
                .build();
    }
}
