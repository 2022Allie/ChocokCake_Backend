package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;

public class AlreadyExistCakeException extends BaseException {
    private AlreadyExistCakeException(){
        super(ErrorCode.ALREADY_EXIST_CAKE);
    }

    private static class SingletonHelper{
        private static final AlreadyExistCakeException INSTANCE = new AlreadyExistCakeException();
    }
    public static AlreadyExistCakeException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
