package com.example.gestiondents.repository;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.entities.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Integer> {

}
