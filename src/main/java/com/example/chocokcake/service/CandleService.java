package com.example.chocokcake.service;

import com.example.chocokcake.controller.dto.CandleListResponse;
import com.example.chocokcake.controller.dto.CandleResponse;
import com.example.chocokcake.entity.Cake;
import com.example.chocokcake.entity.repository.CakeRepository;
import com.example.chocokcake.entity.repository.CandleRepository;
import com.example.chocokcake.entity.repository.UserRepository;
import com.example.chocokcake.exception.BaseException;
import com.example.chocokcake.exception.ErrorCode;
import com.example.chocokcake.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandleService {
    private final CakeRepository cakeRepository;
    private final AuthenticationFacade authenticationFacade;
    private final CandleRepository candleRepository;
    public CandleListResponse getCandleList(Long id){
        if(authenticationFacade.getCurrentUser() == null){
            throw new BaseException(ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION);
        }
        Cake cake = cakeRepository.findById(id)
                .orElseThrow();
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
}
