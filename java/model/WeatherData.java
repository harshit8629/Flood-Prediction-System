package com.flood.FloodPredictionSystem.model;

// This class holds weather data fetched from API
public class WeatherData {

    private String cityName;
    private double rainfall;      // mm
    private double humidity;      // percentage
    private double temperature;   // celsius
    private double waterLevel;    // estimated from rainfall
    private String description;
    private boolean pastFloodHistory;

    public WeatherData() {}

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public double getRainfall() { return rainfall; }
    public void setRainfall(double rainfall) { this.rainfall = rainfall; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getWaterLevel() { return waterLevel; }
    public void setWaterLevel(double waterLevel) { this.waterLevel = waterLevel; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isPastFloodHistory() { return pastFloodHistory; }
    public void setPastFloodHistory(boolean p) { this.pastFloodHistory = p; }
}