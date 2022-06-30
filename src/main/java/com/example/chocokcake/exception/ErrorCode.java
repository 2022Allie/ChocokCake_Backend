package com.example.chocokcake.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATE_MEMBER(400, "Duplicate Member"),
    UN_AUTHORIZED_TOKEN_EXCEPTION(401, "UN_AUTHORIZED_TOKEN_EXCEPTION : 인증되지 않은 토큰입니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVERERROR");

    private final Integer status;
    private final String message;
    ErrorCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
