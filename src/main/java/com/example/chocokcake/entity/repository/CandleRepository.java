package com.example.chocokcake.entity.repository;

import com.example.chocokcake.controller.dto.CandleResponse;
import com.example.chocokcake.entity.Cake;
import com.example.chocokcake.entity.Candle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {
    List<Candle> findByCake(Cake cake);
    void deleteCandlesByCake(Cake cake);
}
