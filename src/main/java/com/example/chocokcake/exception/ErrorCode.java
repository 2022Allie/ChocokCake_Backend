package com.example.chocokcake.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DUPLICATE_MEMBER(400, "Duplicate Member");

    private final Integer status;
    private final String message;
    ErrorCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
