package com.example.chocokcake.global.error.exception;
import com.example.chocokcake.global.error.ErrorCode;
import com.example.chocokcake.global.error.exception.BaseException;
import lombok.Getter;

@Getter
public class UnAuthorizedTokenFilterException extends BaseException {
    public UnAuthorizedTokenFilterException() {
        super(ErrorCode.UN_AUTHORIZED_TOKEN_FILTER_EXCEPTION);
    }
}