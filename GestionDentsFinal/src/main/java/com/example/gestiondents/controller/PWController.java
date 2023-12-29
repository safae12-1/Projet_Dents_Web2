package com.example.gestiondents.controller;

import com.example.gestiondents.entities.PW;
import com.example.gestiondents.entities.Student;
import com.example.gestiondents.repository.GroupeRepository;
import com.example.gestiondents.repository.PWRepository;
import com.example.gestiondents.repository.StudentRepository;
import com.example.gestiondents.repository.ToothRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PWController {

    @Autowired
    private PWRepository pwRepository;


    @Autowired
    private ToothRepository toothRepository; // Assuming you have a ToothRepository
    @Autowired
    private GroupeRepository groupeRepository;
    @GetMapping("/newpw")
    public String showSignUpForm(Model model) {
        model.addAttribute("pw", new PW()); // Add an instance of PW to the model
        model.addAttribute("teeth", toothRepository.findAll()); // Add list of teeth to the model
        model.addAttribute("groups", groupeRepository.findAll());
        return "pw/add-pw";
    }

    @PostMapping("/addpw")
    public String addPw(@Valid PW pw, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pw/add-pw";
        }

        pwRepository.save(pw);
        model.addAttribute("pw", pwRepository.findAll());
        return "pw/index_pw";
    }

    @GetMapping("/editpw/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        PW pw = pwRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pw Id:" + id));
        model.addAttribute("pw", pw);
        model.addAttribute("teeth", toothRepository.findAll());
        model.addAttribute("groups", groupeRepository.findAll());
        return "pw/update-pw";
    }

    @PostMapping("/updatepw/{id}")
    public String updatepw(@PathVariable("id") int id, @Valid PW pw, BindingResult result, Model model) {
        if (result.hasErrors()) {
            pw.setId(id);
            return "pw/update-pw";
        }

        pwRepository.save(pw);
        model.addAttribute("pw", pwRepository.findAll());
        return "pw/index_pw";
    }

    @GetMapping("/deletepw/{id}")
    public String deletepw(@PathVariable("id") int id, Model model) {
        PW pw = pwRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pw Id:" + id));
        pwRepository.delete(pw);
        model.addAttribute("pw", pwRepository.findAll());
        return "pw/index_pw";
    }
    @GetMapping("/acceuilpw")
    public String index(Model model, HttpSession session) {
        if (!isUserLoggedIn(session) || !hasUserRole(session, "professor")) {
            return "redirect:/login"; // Redirect to login if not logged in or not a professor
        }

        model.addAttribute("pw", pwRepository.findAll());
        return "pw/index_pw";
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