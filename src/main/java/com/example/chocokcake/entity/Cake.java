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
public class Cake {
    @Id
    private Long id;
    @Column(name = "theme")
    private Long theme;
    @Column(name = "birth_day")
    private String brithDay;
}
