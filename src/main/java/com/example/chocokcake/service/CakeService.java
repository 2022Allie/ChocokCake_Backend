package com.example.chocokcake.service;

import com.example.chocokcake.controller.dto.MessageResponse;
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
    public MessageResponse createCake(Long theme){
        cakeRepository.save(Cake.builder()
                .theme(theme)
                .user(authenticationFacade.getCurrentUser())
                .build());
        return MessageResponse.builder()
                .message(authenticationFacade.getCurrentUser() + "님의 케이크가 " + theme + "맛으로 생성되었습니다." )
                .build();
    }
}
