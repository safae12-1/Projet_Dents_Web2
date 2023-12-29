package com.example.gestiondents.controller;

import com.example.gestiondents.entities.StudentPW;
import com.example.gestiondents.service.StudentPWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentpws")
public class StudentPWController {

    @Autowired
    private StudentPWService studentPWService;

    @GetMapping
    public List<StudentPW> getAllStudentPWs() {
        return studentPWService.findAllStudentPWs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentPW> getStudentPWById(@PathVariable int id) {
        StudentPW studentPW = studentPWService.findById(id);
        return studentPW != null ?
                new ResponseEntity<>(studentPW, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Void> createStudentPW(@RequestBody StudentPW studentPW) {
        studentPWService.saveStudentPW(studentPW);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudentPW(@PathVariable int id, @RequestBody StudentPW studentPW) {
        StudentPW existingStudentPW = studentPWService.findById(id);
        if (existingStudentPW != null) {
            studentPW.setPk(existingStudentPW.getPk()); // Ensure the primary key is not changed
            studentPWService.updateStudentPW(studentPW);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentPW(@PathVariable int id) {
        StudentPW studentPWToDelete = studentPWService.findById(id);
        if (studentPWToDelete != null) {
            studentPWService.deleteStudentPW(studentPWToDelete);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
