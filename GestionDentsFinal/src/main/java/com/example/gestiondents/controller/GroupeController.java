package com.example.gestiondents.controller;
import com.example.gestiondents.entities.*;
import com.example.gestiondents.repository.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupeController {

    @Autowired
    private GroupeRepository groupeRepository;
    @Autowired
    private PWRepository pwRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProfessorRepository profRepository;
    @Autowired
    private StudentPWRepository studentPWRepository;

    @GetMapping("/newgroupe")
    public String showSignUpForm(Model model) {
        model.addAttribute("groupe", new Groupe());
        model.addAttribute("prof", profRepository.findAll());
        return "groupe/add-groupe";
    }

    @PostMapping("/addgroupe")
    public String addgroupe(@Valid Groupe groupe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "groupe/add-groupe";
        }

        groupeRepository.save(groupe);
        model.addAttribute("groupe", groupeRepository.findAll());
        return "groupe/index_groupe";
    }

    @GetMapping("/editgroupe/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Groupe groupe = groupeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid groupe Id:" + id));
        model.addAttribute("groupe", groupe);
        model.addAttribute("professors", profRepository.findAll());
        return "groupe/update-groupe";

    }

    @PostMapping("/updategroupe/{id}")
    public String updategroupe(@PathVariable("id") int id, @Valid Groupe groupe, BindingResult result, Model model) {
        if (result.hasErrors()) {
            groupe.setId(id);
            return "groupe/update-groupe";
        }

        groupeRepository.save(groupe);
        model.addAttribute("groupe", groupeRepository.findAll());
        return "groupe/index_groupe";
    }

    @GetMapping("/deletegroupe/{id}")
    public String deletegroupe(@PathVariable("id") int id, Model model) {
        Groupe groupe = groupeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid groupe Id:" + id));
        groupeRepository.delete(groupe);
        model.addAttribute("groupe", groupeRepository.findAll());
        return "groupe/index_groupe";
    }
    @GetMapping("/groupe-details/{id}")
    public String showGroupDetails(@PathVariable("id") int id, Model model) {
        Groupe groupe = groupeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid groupe Id:" + id));

        model.addAttribute("group", groupe);

        List<PW> pwList = pwRepository.findPWsByGroup(groupe);
        model.addAttribute("pwList", pwList);
        // Make sure studentPWRepository is passed to the template
        model.addAttribute("studentPWRepository", studentPWRepository);

        model.addAttribute("imageRepository", imageRepository);



        return "groupe/group-details";
    }



    @GetMapping("/acceuilgroupe")
    public String index(Model model, HttpSession session) {
        if (!isUserLoggedIn(session) || !hasUserRole(session, "professor")) {
            return "redirect:/login";
        }

        model.addAttribute("groupe", groupeRepository.findAll());
        return "groupe/index_groupe";
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