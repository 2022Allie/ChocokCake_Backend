package com.example.chocokcake.service;

import com.example.chocokcake.controller.dto.LoginRequest;
import com.example.chocokcake.controller.dto.MessageResponse;
import com.example.chocokcake.controller.dto.TokenResponse;
import com.example.chocokcake.controller.dto.UserRequest;
import com.example.chocokcake.entity.User;
import com.example.chocokcake.entity.repository.UserRepository;
import com.example.chocokcake.exception.BaseException;
import com.example.chocokcake.exception.ErrorCode;
import com.example.chocokcake.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    @Transactional
    public MessageResponse join(UserRequest request) {
        duplicateMemberVerification(request.getAccountId());
        User user = User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .birthDay(request.getBirthDay())
                .build();
        userRepository.save(user);
        return MessageResponse.builder()
                .message(user.getName() + "님이 정상적으로 회원가입 되었습니다.")
                .build();
    }
    private void duplicateMemberVerification(String accountId){ //중복 회원 검증 로직
        userRepository.findByAccountId(accountId)
                .ifPresent(m -> {
                    throw new BaseException(ErrorCode.DUPLICATE_MEMBER);
                });
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_USER));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BaseException(ErrorCode.PASSWORD_NOT_MATCHED);
        }
        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(request.getAccountId()))
                .build();
    }
}
