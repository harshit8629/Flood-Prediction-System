package com.flood.FloodPredictionSystem.service;

import com.flood.FloodPredictionSystem.model.FloodData;
import com.flood.FloodPredictionSystem.model.Prediction;
import com.flood.FloodPredictionSystem.model.User;
import com.flood.FloodPredictionSystem.repository.FloodDataRepository;
import com.flood.FloodPredictionSystem.repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FloodService {

    @Autowired
    private FloodDataRepository floodDataRepository;

    @Autowired
    private PredictionRepository predictionRepository;

    @Autowired
    private PredictionEngine predictionEngine;

    // Save flood data and generate prediction
    public Prediction savePrediction(FloodData floodData, User user) {

        // Set user and time
        floodData.setUser(user);
        floodData.setRecordedAt(LocalDateTime.now());

        // Save flood data to database
        FloodData savedData = floodDataRepository.save(floodData);

        // Run prediction engine
        String risk = predictionEngine.predictRisk(
                floodData.getRainfall(),
                floodData.getWaterLevel(),
                floodData.getHumidity(),
                floodData.isPastFloodHistory()
        );

        String alert       = predictionEngine.getAlertMessage(risk);
        String suggestions = predictionEngine.getSafetySuggestions(risk);

        // Create and save prediction
        Prediction prediction = new Prediction();
        prediction.setUser(user);
        prediction.setFloodData(savedData);
        prediction.setRiskLevel(risk);
        prediction.setAlertMessage(alert);
        prediction.setSafetySuggestions(suggestions);
        prediction.setPredictedAt(LocalDateTime.now());

        return predictionRepository.save(prediction);
    }

    // Get prediction history for a user
    public List<Prediction> getUserHistory(int userId) {
        return predictionRepository.findByUserId(userId);
    }

    // Get all predictions for admin
    public List<Prediction> getAllPredictions() {
        return predictionRepository.findAllByOrderByPredictedAtDesc();
    }

    // Get all flood data for admin
    public List<FloodData> getAllFloodData() {
        return floodDataRepository.findAll();
    }
}