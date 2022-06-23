package com.example.chocokcake.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@Table(name = "candle")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candle {
    @Id
    private Long id;
    @Column(name = "theme")
    private Long theme;
    @Column(name = "postman")
    private String postman;
    @Column(name = "letter")
    private String letter;
}
