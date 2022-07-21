package com.example.chocokcake.domain.entity.candle;

import com.example.chocokcake.domain.entity.cake.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {
    List<Candle> findByCake(Cake cake);
}
