package com.example.chocokcake.entity.repository;

import com.example.chocokcake.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);
    Integer findIdByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
}
