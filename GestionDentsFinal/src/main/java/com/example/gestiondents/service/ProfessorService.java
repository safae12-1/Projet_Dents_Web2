package com.example.gestiondents.service;

import com.example.gestiondents.entities.Professor;
import com.example.gestiondents.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository ;
    public void saveProfessor(Professor e){
        professorRepository.save(e);
    }
    public void updateProfessor(Professor a){
        professorRepository.save(a);
    }
    public List<Professor> findAllProfessors() {
        return  professorRepository.findAll();
    }
    public Professor findById(int id) {
        return professorRepository.findById(id).orElse(null);
    }
    public boolean deleteProfessor(Professor e) {
        try {
            professorRepository.delete(e);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
