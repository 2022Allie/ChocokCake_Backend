package com.example.chocokcake.domain.entity.user;

import com.example.chocokcake.domain.entity.cake.Cake;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "password")
    private String password;

    @Column(name = "birth_day")
    private LocalDate birthDay;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Cake> cakeList;

    public void setBirthDay(LocalDate localDate){
        birthDay = localDate;
    }
}
