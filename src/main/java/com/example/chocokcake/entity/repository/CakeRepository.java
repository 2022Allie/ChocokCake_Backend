package com.example.chocokcake.entity.repository;

import com.example.chocokcake.entity.Cake;
import com.example.chocokcake.entity.Candle;
import com.example.chocokcake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
    List<Cake> findCakesByUser(User user);
}
