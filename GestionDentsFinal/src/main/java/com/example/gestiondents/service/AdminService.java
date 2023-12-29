package com.example.gestiondents.service;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository ;
    public void saveAdmin(Admin admin){
        adminRepository.save(admin);
    }
    public void updateAdmin(Admin a){
         adminRepository.save(a);
    }
    public List<Admin> findAllAdmins() {
        return  adminRepository.findAll();
    }
    public Admin findById(int id) {
        return adminRepository.findById(id).orElse(null);
    }
    public boolean deleteAdmin(Admin e) {
        try {
            adminRepository.delete(e);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
