package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;

public class NotFoundUserException extends BaseException{
    private NotFoundUserException(){
        super(ErrorCode.NOT_FOUND_USER);
    }

    private static class SingletonHelper{
        private static final NotFoundUserException INSTANCE = new NotFoundUserException();
    }
    public static NotFoundUserException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
