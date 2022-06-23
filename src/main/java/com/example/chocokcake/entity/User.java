package com.example.chocokcake.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "password")
    private String password;
    @Column(name = "birth_day")
    private String birthDay;
}
