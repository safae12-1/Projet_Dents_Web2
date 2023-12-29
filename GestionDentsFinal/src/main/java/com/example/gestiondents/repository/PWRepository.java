package com.example.gestiondents.repository;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.entities.Groupe;
import com.example.gestiondents.entities.PW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PWRepository extends JpaRepository<PW, Integer> {
    @Query("SELECT pw FROM PW pw JOIN pw.groupPw g WHERE g = :groupe")
    List<PW> findPWsByGroup(@Param("groupe") Groupe groupe);
}
