package com.flood.FloodPredictionSystem.controller;

import com.flood.FloodPredictionSystem.model.User;
import com.flood.FloodPredictionSystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Home page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // About page
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // Safety page
    @GetMapping("/safety")
    public String safety() {
        return "safety";
    }

    // Show register page
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle register form
    @PostMapping("/register")
    public String doRegister(@ModelAttribute User user, Model model) {
        boolean success = userService.registerUser(user);
        if (success) {
            model.addAttribute("message", "Registration successful! Please login.");
            return "login";
        } else {
            model.addAttribute("error", "Email already exists! Try another.");
            return "register";
        }
    }

    // Show login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // Handle login form
    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {

        User user = userService.loginUser(email, password);
        if (user != null) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password!");
            return "login";
        }
    }

    // Dashboard page
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        model.addAttribute("userName", session.getAttribute("userName"));
        return "dashboard";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}