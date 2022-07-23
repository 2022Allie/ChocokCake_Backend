package com.example.chocokcake.domain.service;

import com.example.chocokcake.domain.controller.dto.request.LetterRequest;
import com.example.chocokcake.domain.controller.dto.response.CandleListResponse;
import com.example.chocokcake.domain.controller.dto.response.CandleResponse;
import com.example.chocokcake.domain.controller.dto.response.LetterResponse;
import com.example.chocokcake.domain.controller.dto.response.MessageResponse;
import com.example.chocokcake.domain.entity.cake.Cake;
import com.example.chocokcake.domain.entity.candle.Candle;
import com.example.chocokcake.domain.entity.cake.CakeRepository;
import com.example.chocokcake.domain.entity.candle.CandleRepository;
import com.example.chocokcake.global.error.exception.*;
import com.example.chocokcake.global.error.exception.AlreadyBirthdayLaterException;
import com.example.chocokcake.global.error.exception.ForbiddenException;
import com.example.chocokcake.global.error.exception.UnAuthorizedTokenException;
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
        Cake cake = cakeRepository.findById(id)
                .orElseThrow(NotFoundCakeException::getInstance);
        if(authenticationFacade.getCurrentUser() != cake.getUser()){
            throw ForbiddenException.getInstance();
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
                .orElseThrow(NotFoundCakeException::getInstance);
        if(cake.getBirthDay().isBefore(LocalDate.now())){
            throw AlreadyBirthdayLaterException.getInstance();
        }
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
                .orElseThrow(NotFoundCandleException::getInstance);
        Cake cake = cakeRepository.findById(candle.getCake().getId())
                .orElseThrow(NotFoundCakeException::getInstance);
        if(!authenticationFacade.getCurrentUser().equals(cake.getUser())){
            throw UnAuthorizedTokenException.getInstance();
        }
        if(cake.getBirthDay().isAfter(LocalDate.now())){
            throw NotYetBirthdayException.getInstance();
        }
        return LetterResponse.builder()
                .letter(candle.getLetter())
                .postman(candle.getPostman())
                .theme(candle.getTheme())
                .build();
    }
}
