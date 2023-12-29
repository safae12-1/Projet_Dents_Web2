package com.example.gestiondents.controller;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.entities.Professor;
import com.example.gestiondents.entities.Student;
import com.example.gestiondents.repository.AdminRepository;
import com.example.gestiondents.repository.ProfessorRepository;
import com.example.gestiondents.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller

public class AdminController { // Renommer la classe de UserController à ToothController

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/newadmin")
    public String showSignUpForm(Admin admin) {
        return "admin/add-admin";
    }

    @PostMapping("/addadmin")
    public String addadmin(@Valid Admin admin, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/add-admin";
        }
        handleImageFile(admin);
        adminRepository.save(admin);
        model.addAttribute("professor", adminRepository.findAll());
        return "admin/index_admin";
    }

    @GetMapping("/editA/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Admin admin =adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid admin Id:" + id));
        model.addAttribute("professor", admin);
        return "admin/update-admin";
    }

    @PostMapping("/updateA/{id}")
    public String updateAdmin(@PathVariable("id") int id, @Valid Admin admin, BindingResult result, Model model) {
        if (result.hasErrors()) {
            admin.setId(id);
            return "admin/update-admin";
        }
        handleImageFile(admin);
        adminRepository.save(admin);
        model.addAttribute("admin", adminRepository.findAll());
        return "admin/index_admin";
    }

    @GetMapping("/deleteA/{id}")
    public String deleteTooth(@PathVariable("id") int id, Model model) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid professor Id:" + id));
        adminRepository.delete(admin);
        model.addAttribute("admin", adminRepository.findAll()); // Remplacer users par teeth
        return "admin/index_admin";
    }
    @GetMapping("/acceuiladmin")
    public String index(Model model) {

        model.addAttribute("admin", adminRepository.findAll());
        return "admin/index_admin";
    }
    private void handleImageFile(Admin admin) {
        MultipartFile imageFile = admin.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                admin.setImage(imageFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }


}
