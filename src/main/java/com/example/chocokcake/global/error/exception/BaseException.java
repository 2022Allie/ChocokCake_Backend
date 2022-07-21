package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode){
        super();
        this.errorCode = errorCode;
    }
}
