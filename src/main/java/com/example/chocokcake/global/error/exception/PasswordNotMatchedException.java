package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;

public class PasswordNotMatchedException extends BaseException{
    private PasswordNotMatchedException(){
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }

    private static class SingletonHelper{
        private static final PasswordNotMatchedException INSTANCE = new PasswordNotMatchedException();
    }
    public static PasswordNotMatchedException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
