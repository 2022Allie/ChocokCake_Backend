package com.example.chocokcake.controller.dto;

<<<<<<< HEAD
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ThemeRequest {
    private Long theme;
=======
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class ThemeRequest {
    private final Long theme;
>>>>>>> 18-create-cake-api
}
