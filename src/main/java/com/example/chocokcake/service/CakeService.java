package com.example.chocokcake.service;

import com.example.chocokcake.controller.dto.*;
import com.example.chocokcake.entity.Cake;
import com.example.chocokcake.entity.User;
import com.example.chocokcake.entity.repository.CakeRepository;
import com.example.chocokcake.exception.BaseException;
import com.example.chocokcake.exception.ErrorCode;
import com.example.chocokcake.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> Stashed changes
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CakeService {
    private final CakeRepository cakeRepository;
    private final AuthenticationFacade authenticationFacade;
    @Transactional
    public MessageResponse createCake(ThemeRequest theme){
        User user = authenticationFacade.getCurrentUser();
        cakeRepository.save(Cake.builder()
                .theme(theme.getTheme())
                .brithDay(user.getBirthDay())
                .user(user)
                .build());
        return MessageResponse.builder()
                .message("케이크가 생성되었습니다." )
                .build();
    }

    @Transactional
    public ReadUserCakeListResponse readMyCake() {
        if(authenticationFacade.getCurrentUser() == null){
            throw new BaseException(ErrorCode.FORBIDDEN);
        }
        User user = authenticationFacade.getCurrentUser();
        return ReadUserCakeListResponse.builder()
                .cakeList(cakeRepository.findCakesByUser(user).stream()
                        .map(cake -> ReadUserCakeResponse.builder()
                                .id(cake.getId())
                                .theme(cake.getTheme())
                                .userName(cake.getUser().getName())
                                .birthDay(cake.getBrithDay())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public ReadCakeDetailsResponse readCakeDetails(Long cakeId) {
        Cake cake = cakeRepository.findById(cakeId)
         .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_CAKE));
        return ReadCakeDetailsResponse.builder()
                .id(cake.getId())
                .birthDay(cake.getBrithDay())
                .userName(cake.getUser().getName())
                .theme(cake.getTheme())
                .build();
    }
}
