package com.flood.FloodPredictionSystem.service;

import com.flood.FloodPredictionSystem.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class WeatherService {

    // Read API key from application.properties
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public WeatherData getWeatherByCity(String city) {
        try {
            // Build API URL
            String url = apiUrl + "?q=" + city +
                    "&appid=" + apiKey +
                    "&units=metric";

            // Call the API
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            // Parse the JSON response
            JSONObject json = new JSONObject(response);

            WeatherData weatherData = new WeatherData();

            // City name
            weatherData.setCityName(json.getString("name"));

            // Temperature
            double temp = json.getJSONObject("main").getDouble("temp");
            weatherData.setTemperature(temp);

            // Humidity
            double humidity = json.getJSONObject("main").getDouble("humidity");
            weatherData.setHumidity(humidity);

            // Rainfall — rain data may not always be present
            double rainfall = 0.0;
            if (json.has("rain")) {
                JSONObject rain = json.getJSONObject("rain");
                if (rain.has("1h")) {
                    rainfall = rain.getDouble("1h");
                } else if (rain.has("3h")) {
                    rainfall = rain.getDouble("3h");
                }
            }
            weatherData.setRainfall(rainfall);

            // Weather description
            String desc = json.getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description");
            weatherData.setDescription(desc);

            // Estimate water level from rainfall
            // Simple formula: higher rainfall = higher estimated water level
            double estimatedWaterLevel = estimateWaterLevel(rainfall, humidity);
            weatherData.setWaterLevel(estimatedWaterLevel);

            return weatherData;

        } catch (Exception e) {
            System.out.println("Weather API error: " + e.getMessage());
            return null;
        }
    }

    // Estimate river water level based on rainfall and humidity
    private double estimateWaterLevel(double rainfall, double humidity) {
        double base = 2.0;
        double fromRain = rainfall * 0.03;
        double fromHumidity = (humidity / 100) * 1.5;
        return Math.round((base + fromRain + fromHumidity) * 10.0) / 10.0;
    }
}