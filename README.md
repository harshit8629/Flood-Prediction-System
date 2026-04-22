# 🌊 Flood Prediction System (API-Based)

A full-stack Flood Prediction Web Application built using Spring Boot, Thymeleaf, HTML, CSS, and MySQL.

🚀 This upgraded system predicts flood risk using real-time environmental data fetched via API based on city or state name input, making it smarter and more realistic.

🚀 Features
👤 User Features
User Registration & Login
Enter City / State Name
Fetch real-time weather & environmental data via API
Get instant flood risk prediction (Low / Medium / High)
View prediction history
Access flood safety tips
Emergency contact information
🛠️ Admin Features
Admin login panel
View all users
View all predictions
Delete users
Monitor system activity
🌐 API Integration

The system uses external APIs (like OpenWeatherMap or similar) to fetch:

Rainfall 🌧️
Humidity 💧
Water level (estimated or derived) 🌊
Weather conditions

📍 User only needs to enter:

City Name / State Name
🧠 Prediction Logic

The system uses a rule-based prediction engine on API data:

🔴 High Risk
Rainfall > 200 mm
Water Level > 7m
High humidity + past flood history
🟠 Medium Risk
Moderate rainfall + water level
🟢 Low Risk
Normal environmental conditions
🏗️ Tech Stack
💻 Frontend
HTML
CSS
Thymeleaf
⚙️ Backend
Java
Spring Boot
Spring MVC
🌐 API
OpenWeatherMap API (or similar weather API)
🗄️ Database
MySQL
Spring Data JPA
🔄 How It Works
User logs in / registers
Enters city/state name
Backend calls weather API
System extracts environmental data
Prediction engine analyzes data
System returns:
Risk Level
Alert Message
Safety Suggestions
Data is stored in database
User can view prediction history
🧪 Example

Input:

City: Mumbai

Fetched Data (via API):

Rainfall: 210 mm
Humidity: 90%
Water Level: High

👉 Output: HIGH RISK 🚨

🆘 Emergency Support
Disaster Helpline: 1078
Police: 100
Ambulance: 108
Fire: 101
⚠️ Future Improvements
Machine Learning model integration
Real-time flood sensor integration
SMS/Email alerts
Google Maps flood zones
Secure authentication (password hashing + JWT)
👨‍💻 Author

Harshit Khanna
B.Tech (AI/ML / CSE DS)

⭐ Project Highlights
API-based real-time prediction
Full-stack development
Real-world problem solving
Clean UI design
Role-based system (User + Admin)
Database integration
Rule-based AI system
📌 How to Run
git clone https://github.com/your-username/flood-prediction-system.git
Open in IDE (IntelliJ / Eclipse)
Add API key in application.properties
Configure MySQL
Run:
FloodPredictionSystemApplication.java
Open:
http://localhost:8080
