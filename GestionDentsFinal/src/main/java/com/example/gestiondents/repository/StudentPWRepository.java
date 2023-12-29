package com.example.gestiondents.repository;

import com.example.gestiondents.entities.Admin;
import com.example.gestiondents.entities.Groupe;
import com.example.gestiondents.entities.StudentPW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPWRepository extends JpaRepository<StudentPW, Integer> {
    List<StudentPW> findByPk_Student_Id(int studentId);
    @Query("SELECT spw FROM StudentPW spw JOIN FETCH spw.imagesList WHERE spw.pk.student.id = :studentId")
    List<StudentPW> findByPk_Student_IdWithImages(@Param("studentId") int studentId);
}
