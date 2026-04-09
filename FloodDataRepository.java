package com.flood.FloodPredictionSystem.repository;

import com.flood.FloodPredictionSystem.model.FloodData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FloodDataRepository extends JpaRepository<FloodData, Integer> {
    List<FloodData> findByUserId(int userId);
}