package com.example.chocokcake.domain.service;

import com.example.chocokcake.domain.controller.dto.request.AccountIdRequest;
import com.example.chocokcake.domain.controller.dto.request.LoginRequest;
import com.example.chocokcake.domain.controller.dto.request.UserRequest;
import com.example.chocokcake.domain.controller.dto.response.MessageResponse;
import com.example.chocokcake.domain.controller.dto.response.TokenResponse;
import com.example.chocokcake.domain.controller.dto.response.UserInfoResponse;
import com.example.chocokcake.domain.entity.user.User;
import com.example.chocokcake.domain.entity.user.UserRepository;
import com.example.chocokcake.global.error.exception.BaseException;
import com.example.chocokcake.global.error.ErrorCode;
import com.example.chocokcake.global.error.exception.DuplicateMemberException;
import com.example.chocokcake.global.error.exception.NotFoundUserException;
import com.example.chocokcake.global.error.exception.PasswordNotMatchedException;
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
                    throw DuplicateMemberException.getInstance();
                });
    }
    @Transactional
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(NotFoundUserException::getInstance);
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordNotMatchedException.getInstance();
        }
        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(request.getAccountId()))
                .build();
    }
    @Transactional
    public MessageResponse withdrawal(LoginRequest request){
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(NotFoundUserException::getInstance);
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordNotMatchedException.getInstance();
        }
        userRepository.deleteById(user.getId());
        return MessageResponse.builder()
                .message("회원님의 계정이 정상적으로 탈퇴되었습니다.")
                .build();
    }

    public UserInfoResponse getUserInfo() {
        User user = authenticationFacade.getCurrentUser();
        return UserInfoResponse.builder()
                .name(user.getName())
                .accountId(user.getAccountId())
                .birthDay(user.getBirthDay())
                .build();
    }
}
