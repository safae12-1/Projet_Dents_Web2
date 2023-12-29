package com.example.gestiondents.controller;

import com.example.gestiondents.entities.User;
import com.example.gestiondents.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return"add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return"add-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return"index-user";
    }
    @GetMapping("/editU/{id}")
    public String showUpdateForm(@PathVariable("id")int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return"update-user";
    }
    @PostMapping("/updateU/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return"update-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return"index_user";
    }

    @GetMapping("/deleteU/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return"index_user";
    }



}
