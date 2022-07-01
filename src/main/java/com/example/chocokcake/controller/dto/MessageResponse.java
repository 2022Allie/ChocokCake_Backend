package com.example.chocokcake.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResponse {
    private final String message;
}
