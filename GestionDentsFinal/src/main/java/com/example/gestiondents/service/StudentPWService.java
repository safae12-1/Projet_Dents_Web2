package com.example.gestiondents.service;

import com.example.gestiondents.entities.StudentPW;
import com.example.gestiondents.repository.StudentPWRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPWService {
    @Autowired
    StudentPWRepository StudentPWRepository ;
    public void saveStudentPW(StudentPW e){
        StudentPWRepository.save(e);
    }
    public void updateStudentPW(StudentPW a){
        StudentPWRepository.save(a);
    }
    public List<StudentPW> findAllStudentPWs() {
        return  StudentPWRepository.findAll();
    }
    public StudentPW findById(int id) {
        return StudentPWRepository.findById(id).orElse(null);
    }
    public boolean deleteStudentPW(StudentPW e) {
        try {
            StudentPWRepository.delete(e);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
