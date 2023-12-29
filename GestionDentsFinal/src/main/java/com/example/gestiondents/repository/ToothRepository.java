package com.example.gestiondents.repository;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.entities.Tooth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToothRepository extends JpaRepository<Tooth, Integer> {
}
