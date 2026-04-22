//package com.flood.FloodPredictionSystem.controller;
//
//import com.flood.FloodPredictionSystem.model.FloodData;
//import com.flood.FloodPredictionSystem.model.Prediction;
//import com.flood.FloodPredictionSystem.model.User;
//import com.flood.FloodPredictionSystem.repository.UserRepository;
//import com.flood.FloodPredictionSystem.service.FloodService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@Controller
//public class FloodController {
//
//    @Autowired
//    private FloodService floodService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Show data entry form
//    @GetMapping("/predict")
//    public String showForm(HttpSession session, Model model) {
//        if (session.getAttribute("userId") == null) {
//            return "redirect:/login";
//        }
//        model.addAttribute("floodData", new FloodData());
//        return "predict";
//    }
//
//    // Handle form submission and show result
//    @PostMapping("/predict")
//    public String doPredict(@ModelAttribute FloodData floodData,
//                            HttpSession session,
//                            Model model) {
//
//        int userId = (int) session.getAttribute("userId");
//        User user = userRepository.findById(userId).orElse(null);
//
//        if (user == null) return "redirect:/login";
//
//        Prediction prediction = floodService.savePrediction(floodData, user);
//
//        model.addAttribute("prediction", prediction);
//        model.addAttribute("floodData", floodData);
//        return "result";
//    }
//
//    // Show history page
//    @GetMapping("/history")
//    public String history(HttpSession session, Model model) {
//        if (session.getAttribute("userId") == null) {
//            return "redirect:/login";
//        }
//        int userId = (int) session.getAttribute("userId");
//        List<Prediction> predictions = floodService.getUserHistory(userId);
//        model.addAttribute("predictions", predictions);
//        return "history";
//    }
//}

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
import java.util.List;

@Controller
public class FloodController {

    @Autowired
    private FloodService floodService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    // Show prediction form — only city input
    @GetMapping("/predict")
    public String showForm(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        return "predict";
    }

    // Handle form — fetch weather automatically then predict
    @PostMapping("/predict")
    public String doPredict(@RequestParam String city,
                            @RequestParam(required = false,
                                    defaultValue = "false")
                            boolean pastFloodHistory,
                            HttpSession session,
                            Model model) {

        // Check login
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        // Fetch live weather data from API
        WeatherData weatherData = weatherService.getWeatherByCity(city);

        // If city not found
        if (weatherData == null) {
            model.addAttribute("error",
                    "City not found! Please check spelling and try again. " +
                            "Try using English name e.g. 'Patna' not 'पटना'");
            return "predict";
        }

        // Get logged in user
        int userId = (int) session.getAttribute("userId");
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return "redirect:/login";

        // Build FloodData from weather API response
        FloodData floodData = new FloodData();
        floodData.setRainfall(weatherData.getRainfall());
        floodData.setWaterLevel(weatherData.getWaterLevel());
        floodData.setHumidity(weatherData.getHumidity());
        floodData.setTemperature(weatherData.getTemperature());
        floodData.setLocation(weatherData.getCityName());
        floodData.setPastFloodHistory(pastFloodHistory);

        // Run prediction engine and save to database
        Prediction prediction = floodService.savePrediction(floodData, user);

        // Send to result page
        model.addAttribute("prediction", prediction);
        model.addAttribute("floodData", floodData);
        model.addAttribute("weatherData", weatherData);

        return "result";
    }

    // Show history page
    @GetMapping("/history")
    public String history(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        int userId = (int) session.getAttribute("userId");
        List<Prediction> predictions = floodService.getUserHistory(userId);
        model.addAttribute("predictions", predictions);
        return "history";
    }
}
