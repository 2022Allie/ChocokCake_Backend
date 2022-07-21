package com.example.chocokcake.domain.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UserInfoResponse {
    private final String name;
    private final String accountId;
    private final LocalDate birthDay;
}
