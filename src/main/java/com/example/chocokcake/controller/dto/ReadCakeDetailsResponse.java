package com.example.chocokcake.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadCakeDetailsResponse {
    private Long id;

    private Long theme;

    private LocalDate birthDay;

    private String userName;
}
