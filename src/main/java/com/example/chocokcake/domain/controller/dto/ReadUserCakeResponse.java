package com.example.chocokcake.domain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadUserCakeResponse {
    private Long id;

    private Long theme;

    private LocalDate birthDay;

    private String userName;
}
