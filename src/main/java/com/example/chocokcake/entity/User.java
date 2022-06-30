package com.example.chocokcake.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Table
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
    private Date birthDay;

    @OneToMany(mappedBy = "user_id")
    private List<Cake> userList;
}
