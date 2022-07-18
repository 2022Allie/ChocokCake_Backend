package com.example.chocokcake.domain.service;

import com.example.chocokcake.domain.controller.dto.*;
import com.example.chocokcake.domain.entity.user.User;
import com.example.chocokcake.domain.entity.cake.CakeRepository;
import com.example.chocokcake.domain.entity.candle.CandleRepository;
import com.example.chocokcake.domain.entity.user.UserRepository;
import com.example.chocokcake.global.exception.costomException.BaseException;
import com.example.chocokcake.global.exception.ErrorCode;
import com.example.chocokcake.global.security.JwtTokenProvider;
import com.example.chocokcake.global.security.auth.AuthenticationFacade;
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
    private final AuthenticationFacade authenticationFacade;
    private final CandleRepository candleRepository;
    private final CakeRepository cakeRepository;

    public MessageResponse checkIdDuplication(AccountIdRequest request) {
        boolean isExists = userRepository.existsByAccountId(request.getAccountId());
        if(isExists) {
            throw new BaseException(ErrorCode.DUPLICATE_MEMBER);
        }
        return MessageResponse.builder()
                .message("사용가능한 아이디입니다")
                .build();
    }

    @Transactional
    public MessageResponse join(UserRequest request) {
        duplicateMemberVerification(request.getAccountId());
        User user = User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .birthDay(authenticationFacade.getBirthDay(request.getMonthOfBirthDay(), request.getDayOfBirthDay()))
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
    @Transactional
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
    @Transactional
    public MessageResponse withdrawal(LoginRequest request){
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND_USER));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BaseException(ErrorCode.PASSWORD_NOT_MATCHED);
        }
        userRepository.deleteById(user.getId());
        return MessageResponse.builder()
                .message("회원님의 계정이 정상적으로 탈퇴되었습니다.")
                .build();
    }
}
