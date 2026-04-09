package com.flood.FloodPredictionSystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flood_data_id")
    private FloodData floodData;

    @Column(name = "risk_level")
    private String riskLevel;

    @Column(name = "alert_message")
    private String alertMessage;

    @Column(name = "safety_suggestions")
    private String safetySuggestions;

    @Column(name = "predicted_at")
    private LocalDateTime predictedAt;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public FloodData getFloodData() { return floodData; }
    public void setFloodData(FloodData f) { this.floodData = f; }
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String r) { this.riskLevel = r; }
    public String getAlertMessage() { return alertMessage; }
    public void setAlertMessage(String a) { this.alertMessage = a; }
    public String getSafetySuggestions() { return safetySuggestions; }
    public void setSafetySuggestions(String s) { this.safetySuggestions = s; }
    public LocalDateTime getPredictedAt() { return predictedAt; }
    public void setPredictedAt(LocalDateTime p) { this.predictedAt = p; }
}