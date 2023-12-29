package com.example.gestiondents.service;

import com.example.gestiondents.entities.Tooth;
import com.example.gestiondents.repository.ToothRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ToothService {
    @Autowired
    ToothRepository ToothRepository ;
    public Tooth saveTooth(Tooth e){
        return ToothRepository.save(e);
    }
    public void updateTooth(Tooth a){
        ToothRepository.save(a);
    }
    public List<Tooth> findAllTooths() {
        return  ToothRepository.findAll();
    }
    public Tooth findById(int id) {
        return ToothRepository.findById(id).orElse(null);
    }
    public boolean deleteTooth(int e) {
        try {
            ToothRepository.deleteById(e);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
