package com.example.chocokcake.entity.repository;

import com.example.chocokcake.entity.Cake;
import com.example.chocokcake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
    Cake findByUserOrderByBrithDayDesc(User user);
    List<Cake> findCakesByUser(User user);

    void deleteCakesByUser(User user);
}
