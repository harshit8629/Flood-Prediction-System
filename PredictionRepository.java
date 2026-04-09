package com.flood.FloodPredictionSystem.repository;

import com.flood.FloodPredictionSystem.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Integer> {
    List<Prediction> findByUserId(int userId);
    List<Prediction> findAllByOrderByPredictedAtDesc();
}