package com.example.chocokcake.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String accountId;
    private String password;
    private String name;
    private Date birthDay;
}
