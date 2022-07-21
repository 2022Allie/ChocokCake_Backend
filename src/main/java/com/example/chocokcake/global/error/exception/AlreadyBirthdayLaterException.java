package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;

public class AlreadyBirthdayLaterException extends BaseException {
    private AlreadyBirthdayLaterException(){
        super(ErrorCode.AlREADY_BIRTHDAY_LATER);
    }

    private static class SingletonHelper{
        private static final AlreadyBirthdayLaterException INSTANCE = new AlreadyBirthdayLaterException();
    }
    public static AlreadyBirthdayLaterException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
