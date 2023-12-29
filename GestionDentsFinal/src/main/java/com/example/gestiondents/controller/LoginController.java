package com.example.gestiondents.controller;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.entities.Professor;
import com.example.gestiondents.entities.User;
import com.example.gestiondents.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepository.findByUserName(username);
        if (user != null && user.getPassword().equals(password)) {
            String role = determineUserRole(user);
            model.addAttribute("username", username);
            model.addAttribute("role", role);

            session.setAttribute("userRole", role);

            if ("admin".equals(role)) {
                return "redirect:/acceuilStudent";
            } else if ("professor".equals(role)) {
                return "redirect:/acceuilTooth";
            } else {
                return "unknown-role";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear the session
        return "redirect:/login";
    }

    private String determineUserRole(User user) {
        if (user instanceof Admin) {
            return "admin";
        } else if (user instanceof Professor) {
            return "professor";
        } else {
            return "unknown";
        }
    }
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }
}
