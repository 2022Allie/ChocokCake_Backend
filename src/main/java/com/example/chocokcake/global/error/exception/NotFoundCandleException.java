package com.example.chocokcake.global.error.exception;

import com.example.chocokcake.global.error.ErrorCode;

public class NotFoundCandleException extends BaseException{
    private NotFoundCandleException(){
        super(ErrorCode.NOT_FOUND_CANDLE);
    }

    private static class SingletonHelper{
        private static final NotFoundCandleException INSTANCE = new NotFoundCandleException();
    }
    public static NotFoundCandleException getInstance(){
        return SingletonHelper.INSTANCE;
    }
}