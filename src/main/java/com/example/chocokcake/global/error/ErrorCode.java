package com.example.chocokcake.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATE_MEMBER(400, "DUPLICATE MEMBER : 중복된 회원입니다."),
    ALREADY_EXIST_CAKE(400,"ALREADY EXIST CAKE : 이미 케이크가 존재합니다."),
    NOT_YET_BIRTHDAY(400,"NOT_YET_BIRTHDAY : 아직 생일이 지나지 않았습니다."),
    AlREADY_BIRTHDAY_LATER(400, "AlREADY_BIRTHDAY_LATER : 이미 생일이 지났습니다."),
    UN_AUTHORIZED_TOKEN_EXCEPTION(401, "UN_AUTHORIZED_TOKEN_EXCEPTION : 인증되지 않은 토큰입니다."),
    UN_AUTHORIZED_TOKEN_FILTER_EXCEPTION(401, "UN_AUTHORIZED_TOKEN_EXCEPTION : 인증되지 않은 토큰입니다."),
    PASSWORD_NOT_MATCHED(401, "PASSWORD_NOT_MATCHED : 비밀번호가 틀렸습니다."),
    FORBIDDEN(403, "FORBIDDEN : 권한 오류"),
    ALREADY_EXIST_TOKEN(403,"ALREADY EXIST TOKEN : 이미 토큰이 존재합니다."),
    NOT_FOUND_CAKE(404, "NOT_FOUND_CAKE : 케이크를 찾을 수 없습니다."),
    NOT_FOUND_USER(404, "NOT_FOUND_USER : 사용자를 찾을 수 없습니다."),
    NOT_FOUND_CANDLE(404, "NOT_FOUND_CANDLE : 초를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVERERROR");

    private final Integer status;
    private final String message;
    ErrorCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
