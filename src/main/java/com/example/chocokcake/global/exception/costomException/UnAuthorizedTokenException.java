package com.example.chocokcake.global.exception.costomException;
import com.example.chocokcake.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UnAuthorizedTokenException extends BaseException {
    public UnAuthorizedTokenException() {
        super(ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION);
    }
}
