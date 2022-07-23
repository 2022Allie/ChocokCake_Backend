package com.example.chocokcake.domain.service;

import com.example.chocokcake.domain.controller.dto.request.ThemeRequest;
import com.example.chocokcake.domain.controller.dto.response.MessageResponse;
import com.example.chocokcake.domain.controller.dto.response.ReadCakeDetailsResponse;
import com.example.chocokcake.domain.controller.dto.response.ReadUserCakeListResponse;
import com.example.chocokcake.domain.controller.dto.response.ReadUserCakeResponse;
import com.example.chocokcake.domain.entity.cake.Cake;
import com.example.chocokcake.domain.entity.user.User;
import com.example.chocokcake.domain.entity.cake.CakeRepository;
import com.example.chocokcake.global.error.exception.*;
import com.example.chocokcake.global.error.exception.AlreadyExistCakeException;
import com.example.chocokcake.global.error.exception.ForbiddenException;
import com.example.chocokcake.global.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CakeService {
    private final CakeRepository cakeRepository;
    private final AuthenticationFacade authenticationFacade;
    @Transactional
    public MessageResponse createCake(ThemeRequest theme){
        if(authenticationFacade.getCurrentUser()==null){
            throw NotFoundUserException.getInstance();
        }
        User user = authenticationFacade.getCurrentUser();
        List<Cake> cakeList = cakeRepository.findCakesByUser(user);
        if(user.getBirthDay().isBefore(LocalDate.now())){
            user.setBirthDay(getBirthDayNow(user.getBirthDay().getMonth(), user.getBirthDay().getDayOfMonth()));
        }
        if(cakeList.size()-1 >= 0){
            Cake lastCake = cakeList.get(cakeList.size()-1);
            if(user.getBirthDay().isEqual(lastCake.getBirthDay())){
                throw AlreadyExistCakeException.getInstance();
            }
        }
        cakeRepository.save(Cake.builder()
                .theme(theme.getTheme())
                .birthDay(user.getBirthDay())
                .user(user)
                .build());
        return MessageResponse.builder()
                .message("케이크가 생성되었습니다." )
                .build();
    }
    private LocalDate getBirthDayNow(Month month, Integer day){
        LocalDate birthDay = LocalDate.of(LocalDate.now().getYear(), month, day);
        if (birthDay.isBefore(LocalDate.now())) {
            birthDay = birthDay.plusYears(1);
        }
        return birthDay;
    }
    @Transactional
    public ReadUserCakeListResponse readMyCake() {
        if(authenticationFacade.getCurrentUser() == null){
            throw ForbiddenException.getInstance();
        }
        User user = authenticationFacade.getCurrentUser();
        return ReadUserCakeListResponse.builder()
                .cakeList(cakeRepository.findCakesByUser(user).stream()
                        .map(cake -> ReadUserCakeResponse.builder()
                                .id(cake.getId())
                                .theme(cake.getTheme())
                                .userName(cake.getUser().getName())
                                .birthDay(cake.getBirthDay())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public ReadCakeDetailsResponse readCakeDetails(Long cakeId) {
        Cake cake = cakeRepository.findById(cakeId)
         .orElseThrow(NotFoundCakeException::getInstance);
        return ReadCakeDetailsResponse.builder()
                .id(cake.getId())
                .birthDay(cake.getBirthDay())
                .userName(cake.getUser().getName())
                .theme(cake.getTheme())
                .build();
    }
}
