# Flood-Prediction-System
# 🌊 Flood Prediction System

A full-stack **Flood Prediction Web Application** built using **Spring Boot, Thymeleaf, HTML, CSS, and MySQL**.
This system predicts flood risk based on environmental data and provides safety alerts and recommendations.

---

## 🚀 Features

### 👤 User Features

* User Registration & Login
* Enter environmental data (rainfall, water level, humidity, etc.)
* Get **instant flood risk prediction** (Low / Medium / High)
* View **prediction history**
* Access **flood safety tips**
* Emergency contact information

### 🛠️ Admin Features

* Admin login panel
* View all users
* View all predictions
* Delete users
* Monitor system activity

---

## 🧠 Prediction Logic

The system uses a **rule-based prediction engine**:

* High Risk:

  * Rainfall > 200 mm & Water Level > 7m
  * High humidity + past flood history
* Medium Risk:

  * Moderate rainfall + water level
* Low Risk:

  * Normal conditions

Based on this, the system generates:

* Risk Level
* Alert Message
* Safety Suggestions

---

## 🏗️ Tech Stack

### 💻 Frontend

* HTML
* CSS
* Thymeleaf

### ⚙️ Backend

* Java
* Spring Boot
* Spring MVC

### 🗄️ Database

* MySQL
* Spring Data JPA

---

## 📂 Project Structure

```
FloodPredictionSystem/
│
├── controller/
│   ├── UserController.java
│   ├── FloodController.java
│   └── AdminController.java
│
├── service/
│   ├── UserService.java
│   ├── FloodService.java
│   └── PredictionEngine.java
│
├── repository/
│   ├── UserRepository.java
│   ├── FloodDataRepository.java
│   └── PredictionRepository.java
│
├── model/
│   ├── User.java
│   ├── FloodData.java
│   └── Prediction.java
│
├── templates/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── predict.html
│   ├── result.html
│   ├── history.html
│   ├── safety.html
│   ├── admin-login.html
│   ├── admin-dashboard.html
│   └── about.html
│
├── static/css/
│   └── style.css
│
└── FloodPredictionSystemApplication.java
```

---

## 🔄 How It Works

1. User logs in / registers
2. Enters flood-related data
3. Data is sent to backend
4. Prediction engine analyzes input
5. System returns:

   * Risk Level
   * Alert Message
   * Safety Suggestions
6. Data is saved in database
7. User can view prediction history

---

## 🧪 Example Input

* Rainfall: 180 mm
* Water Level: 6.5 m
* Humidity: 85%
* Past Flood History: Yes

👉 Output: **HIGH RISK**

---

## 🆘 Emergency Support

* Disaster Helpline: 1078
* Police: 100
* Ambulance: 108
* Fire: 101

---

## ⚠️ Future Improvements

* Machine Learning model integration
* Real-time weather API
* SMS/Email alerts
* Google Maps integration
* Secure authentication (password hashing)

---

## 👨‍💻 Author

**Harshit Khanna**
B.Tech (AI/ML / CSE DS)

---

## ⭐ Project Highlights

* Full-stack development
* Real-world problem solving
* Clean UI design
* Role-based system (User + Admin)
* Database integration
* Rule-based AI system

---

## 📌 How to Run

1. Clone the repository:

```bash
git clone https://github.com/your-username/flood-prediction-system.git
```

2. Open in IDE (IntelliJ / Eclipse)

3. Configure MySQL in `application.properties`

4. Run:

```bash
FloodPredictionSystemApplication.java
```

5. Open browser:

```
http://localhost:8080
```

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
