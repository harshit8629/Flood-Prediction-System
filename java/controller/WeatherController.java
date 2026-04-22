package com.flood.FloodPredictionSystem.controller;

import com.flood.FloodPredictionSystem.model.FloodData;
import com.flood.FloodPredictionSystem.model.Prediction;
import com.flood.FloodPredictionSystem.model.User;
import com.flood.FloodPredictionSystem.model.WeatherData;
import com.flood.FloodPredictionSystem.repository.UserRepository;
import com.flood.FloodPredictionSystem.service.FloodService;
import com.flood.FloodPredictionSystem.service.WeatherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private FloodService floodService;

    @Autowired
    private UserRepository userRepository;

    // Show weather prediction page
    @GetMapping("/weather-predict")
    public String showWeatherPage(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        return "weather-predict";
    }

    // Fetch weather and predict flood
    @PostMapping("/weather-predict")
    public String doWeatherPredict(@RequestParam String city,
                                   @RequestParam(required = false,
                                           defaultValue = "false")
                                   boolean pastFloodHistory,
                                   HttpSession session,
                                   Model model) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        // Fetch weather from API
        WeatherData weatherData = weatherService.getWeatherByCity(city);

        if (weatherData == null) {
            model.addAttribute("error",
                    "City not found! Please check the city name and try again.");
            return "weather-predict";
        }

        weatherData.setPastFloodHistory(pastFloodHistory);

        // Get logged in user
        int userId = (int) session.getAttribute("userId");
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return "redirect:/login";

        // Create FloodData from weather API response
        FloodData floodData = new FloodData();
        floodData.setRainfall(weatherData.getRainfall());
        floodData.setWaterLevel(weatherData.getWaterLevel());
        floodData.setHumidity(weatherData.getHumidity());
        floodData.setTemperature(weatherData.getTemperature());
        floodData.setLocation(weatherData.getCityName());
        floodData.setPastFloodHistory(pastFloodHistory);

        // Run prediction
        Prediction prediction = floodService.savePrediction(floodData, user);

        // Send everything to result page
        model.addAttribute("prediction", prediction);
        model.addAttribute("floodData", floodData);
        model.addAttribute("weatherData", weatherData);
        model.addAttribute("autoFetched", true);

        return "result";
    }
}