package com.example.chocokcake.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candle")
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

    @ManyToOne
    @JoinColumn(name = "cake_id")
    private Cake cakeId;
}
