package com.example.chocokcake.exception.costomException;

import com.example.chocokcake.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode){
        super();
        this.errorCode = errorCode;
    }
}
