package com.example.chocokcake.domain.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResponse {
    private final String message;
}
