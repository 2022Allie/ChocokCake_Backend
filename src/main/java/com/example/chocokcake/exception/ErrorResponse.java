package com.example.chocokcake.exception;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    public String message;

    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .message(errorCode.getMessage())
                .build();
    }
}
