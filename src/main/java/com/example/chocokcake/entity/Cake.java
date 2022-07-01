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

<<<<<<< HEAD
    @OneToMany(mappedBy = "cake")
=======
    @OneToMany(mappedBy = "cake_id")
>>>>>>> 16-cake-candle-mapping
    private List<Candle> candleList;
}
