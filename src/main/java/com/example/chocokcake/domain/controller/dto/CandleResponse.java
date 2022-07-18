package com.example.chocokcake.domain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CandleResponse {
    private Long id;
    private String postman;
    private Long theme;
}
