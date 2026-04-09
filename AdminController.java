package com.flood.FloodPredictionSystem.controller;

import com.flood.FloodPredictionSystem.service.FloodService;
import com.flood.FloodPredictionSystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private FloodService floodService;

    // Show admin login page
    @GetMapping("/login")
    public String showAdminLogin() {
        return "admin-login";
    }

    // Handle admin login
    @PostMapping("/login")
    public String doAdminLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        if (username.equals("admin") && password.equals("admin123")) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid admin credentials!");
            return "admin-login";
        }
    }

    // Admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("predictions", floodService.getAllPredictions());
        model.addAttribute("floodDataList", floodService.getAllFloodData());
        return "admin-dashboard";
    }

    // Delete user
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable int id,
                             HttpSession session) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }
        userService.deleteUser(id);
        return "redirect:/admin/dashboard";
    }

    // Admin logout
    @GetMapping("/logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute("adminLoggedIn");
        return "redirect:/admin/login";
    }
}