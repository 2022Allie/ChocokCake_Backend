package com.example.chocokcake.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATE_MEMBER(400, "DUPLICATE MEMBER"),
    UN_AUTHORIZED_TOKEN_EXCEPTION(401, "UN_AUTHORIZED_TOKEN_EXCEPTION : 인증되지 않은 토큰입니다."),
    PASSWORD_NOT_MATCHED(401, "PASSWORD_NOT_MATCHED : 비밀번호가 틀렸습니다."),
    FORBIDDEN(403, "FORBIDDEN : 권한 오류"),
    NOT_FOUND_CAKE(404, "NOT_FOUND_CAKE : 케이크를 찾을 수 없습니다."),
    NOT_FOUND_USER(404, "NOT_FOUND_USER : 사용자를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVERERROR");

    private final Integer status;
    private final String message;
    ErrorCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
