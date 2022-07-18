package com.example.chocokcake.domain.entity.cake;

import com.example.chocokcake.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
    List<Cake> findCakesByUser(User user);
}
