package com.example.gestiondents.service;

import com.example.gestiondents.entities.User;
import com.example.gestiondents.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository UserRepository ;
    public void saveUser(User e){
        UserRepository.save(e);
    }
    public void updateUser(User a){
        UserRepository.save(a);
    }
    public List<User> findAllUsers() {
        return  UserRepository.findAll();
    }
    public User findById(int id) {
        return UserRepository.findById(id).orElse(null);
    }
    public boolean deleteUser(int e) {
        try {
            UserRepository.deleteById(e);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
