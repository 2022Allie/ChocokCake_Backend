package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;

public class ForbiddenException extends BaseException {
    private ForbiddenException(){
        super(ErrorCode.FORBIDDEN);
    }

    private static class SingletonHelper{
        private static final ForbiddenException INSTANCE = new ForbiddenException();
    }
    public static ForbiddenException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
