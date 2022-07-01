package com.example.chocokcake.service;

import com.example.chocokcake.controller.dto.MessageResponse;
import com.example.chocokcake.controller.dto.ReadUserCakeResponse;
import com.example.chocokcake.controller.dto.ThemeRequest;
import com.example.chocokcake.entity.Cake;
import com.example.chocokcake.entity.User;
import com.example.chocokcake.entity.repository.CakeRepository;
import com.example.chocokcake.security.auth.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public ReadUserCakeResponse readMyCake() {
        User user = authenticationFacade.getCurrentUser();

        Cake cake = cakeRepository.findByUserOrderByBrithDayDesc(user);
        return ReadUserCakeResponse.builder()
                .id(cake.getId())
                .birthDay(cake.getBrithDay())
                .userName(user.getName())
                .theme(cake.getTheme())
                .build();
    }
}
