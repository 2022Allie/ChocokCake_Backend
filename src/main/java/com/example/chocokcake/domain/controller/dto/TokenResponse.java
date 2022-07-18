package com.example.chocokcake.domain.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponse {

    private final String accessToken;
}
