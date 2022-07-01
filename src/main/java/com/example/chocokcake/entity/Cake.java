package com.example.chocokcake.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theme")
    private Long theme;

    @Column(name = "birth_day")
    private String brithDay;

    @OneToMany(mappedBy = "cake")
    private List<Candle> candleList;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
