package com.example.chocokcake.security.auth;

import com.example.chocokcake.entity.User;
import com.example.chocokcake.entity.repository.UserRepository;
import com.example.chocokcake.exception.BaseException;
import com.example.chocokcake.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final UserRepository userRepository;
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        return userRepository.findByAccountId(authDetails.getUsername())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_USER));
    }
    public LocalDate getBirthDay(Integer month, Integer day){
        LocalDate birthDay = LocalDate.of(LocalDate.now().getYear(), month, day);
        if (birthDay.isBefore(LocalDate.now())) {
            birthDay = birthDay.plusYears(1);
        }
        return birthDay;
    }
}
