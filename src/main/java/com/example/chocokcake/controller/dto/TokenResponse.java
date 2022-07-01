package com.example.chocokcake.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponse {

    private final String accessToken;
}
