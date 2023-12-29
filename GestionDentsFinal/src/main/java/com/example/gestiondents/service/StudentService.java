package com.example.gestiondents.service;

import com.example.gestiondents.entities.Student;
import com.example.gestiondents.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository StudentRepository ;
    public void saveStudent(Student e){
        StudentRepository.save(e);
    }
    public void updateStudent(Student a){
        StudentRepository.save(a);
    }
    public List<Student> findAllStudents() {
        return  StudentRepository.findAll();
    }
    public Student findById(int id) {
        return StudentRepository.findById(id).orElse(null);
    }
    public boolean deleteStudent(Student e) {
        try {
            StudentRepository.delete(e);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
