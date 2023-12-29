package com.example.gestiondents.service;

import com.example.gestiondents.entities.PW;
import com.example.gestiondents.repository.PWRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PWService {
    @Autowired
    PWRepository PWRepository ;
    public void savePW(PW e){
        PWRepository.save(e);
    }
    public void updatePW(PW a){
        PWRepository.save(a);
    }
    public List<PW> findAllPWs() {
        return  PWRepository.findAll();
    }
    public PW findById(int id) {
        return PWRepository.findById(id).orElse(null);
    }
    public boolean deletePW(PW e) {
        try {
            PWRepository.delete(e);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
