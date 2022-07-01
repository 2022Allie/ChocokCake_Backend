package com.example.chocokcake.entity.repository;

import com.example.chocokcake.entity.Cake;
import com.example.chocokcake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
    Cake findByUserOrderByBrithDayDesc(User user);
}
