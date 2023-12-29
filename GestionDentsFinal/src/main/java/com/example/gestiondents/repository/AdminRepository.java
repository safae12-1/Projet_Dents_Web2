package com.example.gestiondents.repository;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUserName(String username);

}
