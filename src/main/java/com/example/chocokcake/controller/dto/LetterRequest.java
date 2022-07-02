package com.example.chocokcake.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LetterRequest {
    private Long theme;

    private String postman;

    private String letter;
}
