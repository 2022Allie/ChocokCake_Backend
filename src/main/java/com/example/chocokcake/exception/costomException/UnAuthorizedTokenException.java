package com.example.chocokcake.exception.costomException;
import com.example.chocokcake.exception.ErrorCode;
import com.example.chocokcake.exception.costomException.BaseException;
import lombok.Getter;

@Getter
public class UnAuthorizedTokenException extends BaseException {
    public UnAuthorizedTokenException() {
        super(ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION);
    }
}
