package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;

public class NotYetBirthdayException extends BaseException {
    private NotYetBirthdayException(){
        super(ErrorCode.NOT_YET_BIRTHDAY);
    }

    private static class SingletonHelper{
        private static final NotYetBirthdayException INSTANCE = new NotYetBirthdayException();
    }
    public static NotYetBirthdayException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
