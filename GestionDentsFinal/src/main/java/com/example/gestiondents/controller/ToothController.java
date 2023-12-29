package com.example.gestiondents.controller;

import com.example.gestiondents.entities.Tooth;
import com.example.gestiondents.repository.ToothRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ToothController { // Renommer la classe de UserController à ToothController

    @Autowired
    private ToothRepository toothRepository;

    @GetMapping("/newtooth")
    public String showSignUpForm(Tooth tooth) {
        return "tooth/add-tooth";
    }

    @PostMapping("/addtooth")
    public String addTooth(@Valid Tooth tooth, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "tooth/add-tooth";
        }

        toothRepository.save(tooth);
        model.addAttribute("teeth", toothRepository.findAll());
        return "tooth/index_tooth";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Tooth tooth = toothRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tooth Id:" + id));
        model.addAttribute("tooth", tooth);
        return "tooth/update-tooth";
    }

    @PostMapping("/update/{id}")
    public String updateTooth(@PathVariable("id") int id, @Valid Tooth tooth, BindingResult result, Model model) {
        if (result.hasErrors()) {
            tooth.setId(id);
            return "tooth/update-tooth";
        }

        toothRepository.save(tooth);
        model.addAttribute("teeth", toothRepository.findAll());
        return "tooth/index_tooth";
    }

    @GetMapping("/delete/{id}")
    public String deleteTooth(@PathVariable("id") int id, Model model) {
        Tooth tooth = toothRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tooth Id:" + id));
        toothRepository.delete(tooth);
        model.addAttribute("teeth", toothRepository.findAll()); // Remplacer users par teeth
        return "tooth/index_tooth";
    }
    @GetMapping("/acceuilTooth")
    public String index(Model model, HttpSession session) {
        if (!isUserLoggedIn(session) || !hasUserRole(session, "professor")) {
            return "redirect:/login"; // Redirect to login if not logged in or not a professor
        }

        model.addAttribute("teeth", toothRepository.findAll());
        return "tooth/index_tooth";
    }
    private boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("userRole") != null;
    }

    // Helper method to check if a user has a specific role
    private boolean hasUserRole(HttpSession session, String role) {
        String userRole = (String) session.getAttribute("userRole");
        return userRole != null && userRole.equals(role);
    }


}