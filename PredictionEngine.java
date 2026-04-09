package com.flood.FloodPredictionSystem.service;

import org.springframework.stereotype.Service;

@Service
public class PredictionEngine {

    // Main prediction logic
    public String predictRisk(double rainfall, double waterLevel,
                              double humidity, boolean pastHistory) {

        // HIGH RISK
        if (rainfall > 200 && waterLevel > 7.0) return "HIGH";
        if (rainfall > 150 && waterLevel > 5.5 && humidity > 85) return "HIGH";
        if (pastHistory && rainfall > 180) return "HIGH";

        // MEDIUM RISK
        if (rainfall > 100 && waterLevel > 4.0) return "MEDIUM";
        if (rainfall > 80 && humidity > 75 && pastHistory) return "MEDIUM";

        // LOW RISK
        return "LOW";
    }

    // Alert message based on risk
    public String getAlertMessage(String risk) {
        switch (risk) {
            case "HIGH":
                return "DANGER! Immediate evacuation recommended. Flood is imminent!";
            case "MEDIUM":
                return "WARNING! Stay alert. Prepare emergency kit and monitor updates.";
            default:
                return "SAFE. Conditions are normal. No immediate action required.";
        }
    }

    // Safety suggestions based on risk
    public String getSafetySuggestions(String risk) {
        switch (risk) {
            case "HIGH":
                return "1. Evacuate to higher ground immediately.\n" +
                        "2. Avoid roads and bridges.\n" +
                        "3. Disconnect all electrical appliances.\n" +
                        "4. Call emergency services: 112\n" +
                        "5. Take important documents with you.";
            case "MEDIUM":
                return "1. Keep emergency bag ready.\n" +
                        "2. Store clean drinking water.\n" +
                        "3. Monitor weather updates every hour.\n" +
                        "4. Know your nearest evacuation route.\n" +
                        "5. Charge your phone fully.";
            default:
                return "1. Conditions are stable.\n" +
                        "2. Stay informed via local weather bulletins.\n" +
                        "3. Keep emergency contacts handy.\n" +
                        "4. Do not block drainage systems.\n" +
                        "5. Report any unusual water rise to authorities.";
        }
    }
}
