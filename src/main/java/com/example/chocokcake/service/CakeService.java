package com.example.chocokcake.service;

import com.example.chocokcake.controller.dto.MessageResponse;
import com.example.chocokcake.controller.dto.ThemeRequest;
import com.example.chocokcake.entity.Cake;
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
        cakeRepository.save(Cake.builder()
                .theme(theme.getTheme())
                .user(authenticationFacade.getCurrentUser())
                .build());
        return MessageResponse.builder()
                .message("케이크가 생성되었습니다." )
                .build();
    }
}
