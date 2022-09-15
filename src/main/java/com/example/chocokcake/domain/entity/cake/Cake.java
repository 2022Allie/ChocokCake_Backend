package com.example.chocokcake.domain.entity.cake;

import com.example.chocokcake.domain.entity.candle.Candle;
import com.example.chocokcake.domain.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "cake")
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
    private LocalDate birthDay;

    @OneToMany(mappedBy = "cake", cascade = CascadeType.REMOVE)
    private List<Candle> candleList;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
