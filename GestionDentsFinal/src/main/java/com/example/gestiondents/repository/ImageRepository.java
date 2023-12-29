package com.example.gestiondents.repository;

import com.example.gestiondents.entities.Image;
import com.example.gestiondents.entities.StudentPW;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByStudentimg(StudentPW studentPW);
}
