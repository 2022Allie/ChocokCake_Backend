package com.example.chocokcake.exception;

import com.example.chocokcake.exception.costomException.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity exception(BaseException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().getMessage());
    }
    @ExceptionHandler(value = ClassCastException.class)
    public ResponseEntity authorizationException(){
        ErrorCode errorCode = ErrorCode.UN_AUTHORIZED_TOKEN_EXCEPTION;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorCode.getMessage());
    }
    @ExceptionHandler(value = SignatureException.class)
    public ResponseEntity signatureException(){
        ErrorCode errorCode = ErrorCode.ALREADY_EXIST_TOKEN;
        return ResponseEntity.status(errorCode.getStatus())
                .body(errorCode.getMessage());
    }
}
