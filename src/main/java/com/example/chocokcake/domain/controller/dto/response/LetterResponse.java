package com.example.chocokcake.domain.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LetterResponse {
    private Long theme;

    private String postman;

    private String letter;
}
