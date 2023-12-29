package com.example.gestiondents.controller;

import com.example.gestiondents.entities.Professor;
import com.example.gestiondents.entities.Student;
import com.example.gestiondents.repository.ProfessorRepository;
import com.example.gestiondents.repository.StudentRepository;
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
public class ProfessorController { // Renommer la classe de UserController à ToothController

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/newprofessor")
    public String showSignUpForm(Professor professor) {
        return "professor/add-professor";
    }

    @PostMapping("/addprofessor")
    public String addProfessor(@Valid Professor professor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "professor/add-professor";
        }
        handleImageFile(professor);
        professorRepository.save(professor);
        model.addAttribute("professor", professorRepository.findAll());
        return "professor/index_professor";
    }

    @GetMapping("/editP/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Professor professor =professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid professor Id:" + id));
        model.addAttribute("professor", professor);
        return "professor/update-professor";
    }

    @PostMapping("/updateP/{id}")
    public String updateProfessor(@PathVariable("id") int id, @Valid Professor professor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            professor.setId(id);
            return "professor/update-professor";
        }
        handleImageFile(professor);
        professorRepository.save(professor);
        model.addAttribute("professor", professorRepository.findAll());
        return "professor/index_professor";
    }

    @GetMapping("/deleteP/{id}")
    public String deleteTooth(@PathVariable("id") int id, Model model) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid professor Id:" + id));
        professorRepository.delete(professor);
        model.addAttribute("professor", professorRepository.findAll()); // Remplacer users par teeth
        return "professor/index_professor";
    }
    @GetMapping("/acceuilprofessor")
    public String index(Model model, HttpSession session) {
        if (!isUserLoggedIn(session) || !hasUserRole(session, "admin")) {
            return "redirect:/login"; // Redirect to login if not logged in or not a professor
        }

        model.addAttribute("professor", professorRepository.findAll());
        return "professor/index_professor";
    }
    private void handleImageFile(Professor professor) {
        MultipartFile imageFile = professor.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                professor.setImage(imageFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
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