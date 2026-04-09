package com.flood.FloodPredictionSystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flood_data")
public class FloodData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double rainfall;

    @Column(name = "water_level")
    private double waterLevel;

    private double humidity;
    private double temperature;
    private String location;

    @Column(name = "past_flood_history")
    private boolean pastFloodHistory;

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public double getRainfall() { return rainfall; }
    public void setRainfall(double rainfall) { this.rainfall = rainfall; }
    public double getWaterLevel() { return waterLevel; }
    public void setWaterLevel(double waterLevel) { this.waterLevel = waterLevel; }
    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public boolean isPastFloodHistory() { return pastFloodHistory; }
    public void setPastFloodHistory(boolean h) { this.pastFloodHistory = h; }
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime r) { this.recordedAt = r; }
}