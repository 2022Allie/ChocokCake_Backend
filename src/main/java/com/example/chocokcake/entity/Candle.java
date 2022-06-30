package com.example.chocokcake.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "theme")
    private Long theme;
    @Column(name = "postman")
    private String postman;
    @Column(name = "letter")
    private String letter;
}
