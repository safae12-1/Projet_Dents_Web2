package com.example.gestiondents.controller;

import com.example.gestiondents.entities.Groupe;
import com.example.gestiondents.entities.Student;
import com.example.gestiondents.repository.GroupeRepository;
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
public class StudentController { // Renommer la classe de UserController à ToothController

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GroupeRepository groupeRepository ;

    @GetMapping("/newstudent")
    public String showSignUpForm(Model model) {
        model.addAttribute("student", new Student()); // Add an instance of groupe to the model
        model.addAttribute("groupe", groupeRepository.findAll()); // Add list of teeth to the model
        return "student/add-student";
    }
    @PostMapping("/addstudent")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student/add-student";
        }
        handleImageFile(student);
        studentRepository.save(student);
        model.addAttribute("student", studentRepository.findAll());
        return "student/index_student";
    }

    @GetMapping("/editS/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        model.addAttribute("groups", groupeRepository.findAll());
        return "student/update-student";
    }

    @PostMapping("/updateS/{id}")
    public String updateStudent(@PathVariable("id") int id, @Valid Student student, BindingResult result, Model model) {

        handleImageFile(student);
        studentRepository.save(student);
        model.addAttribute("student", studentRepository.findAll());
        return "student/index_student";
    }

    @GetMapping("/deleteS/{id}")
    public String deleteTooth(@PathVariable("id") int id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Student Id:" + id));
        studentRepository.delete(student);
        model.addAttribute("student", studentRepository.findAll()); // Remplacer users par teeth
        return "student/index_student";
    }
    @GetMapping("/acceuilStudent")
    public String index(Model model, HttpSession session) {
        if (!isUserLoggedIn(session) || !hasUserRole(session, "admin")) {
            return "redirect:/login"; // Redirect to login if not logged in or not a professor
        }

        model.addAttribute("student", studentRepository.findAll());
        return "student/index_student";
    }
    private void handleImageFile(Student student) {
        MultipartFile imageFile = student.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                student.setImage(imageFile.getBytes());
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