package com.example.gestiondents.repository;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.entities.Professor;
import com.example.gestiondents.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Professor findByUserName(String username);

}
