package com.example.chocokcake.domain.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String accountId;

    private String password;

    private String name;

    private Integer monthOfBirthDay;

    private Integer dayOfBirthDay;
}
