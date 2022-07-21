package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;

public class NotFoundCakeException extends BaseException{
    private NotFoundCakeException(){
        super(ErrorCode.NOT_FOUND_CAKE);
    }

    private static class SingletonHelper{
        private static final NotFoundCakeException INSTANCE = new NotFoundCakeException();
    }
    public static NotFoundCakeException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
