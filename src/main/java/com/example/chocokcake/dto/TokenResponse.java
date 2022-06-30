package com.example.chocokcake.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponse {

    private final String accessToken;
}
