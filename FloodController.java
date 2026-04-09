package com.flood.FloodPredictionSystem.controller;

import com.flood.FloodPredictionSystem.model.FloodData;
import com.flood.FloodPredictionSystem.model.Prediction;
import com.flood.FloodPredictionSystem.model.User;
import com.flood.FloodPredictionSystem.repository.UserRepository;
import com.flood.FloodPredictionSystem.service.FloodService;
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

    // Show data entry form
    @GetMapping("/predict")
    public String showForm(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        model.addAttribute("floodData", new FloodData());
        return "predict";
    }

    // Handle form submission and show result
    @PostMapping("/predict")
    public String doPredict(@ModelAttribute FloodData floodData,
                            HttpSession session,
                            Model model) {

        int userId = (int) session.getAttribute("userId");
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) return "redirect:/login";

        Prediction prediction = floodService.savePrediction(floodData, user);

        model.addAttribute("prediction", prediction);
        model.addAttribute("floodData", floodData);
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