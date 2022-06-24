package com.example.chocokcake.service;

import com.example.chocokcake.dto.MessageResponse;
import com.example.chocokcake.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public MessageResponse join(String request) {
        return MessageResponse.builder().build();
    }
}
