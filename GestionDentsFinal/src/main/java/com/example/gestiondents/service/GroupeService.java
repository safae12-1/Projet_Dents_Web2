package com.example.gestiondents.service;

import com.example.gestiondents.entities.Groupe;
import com.example.gestiondents.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeService {
    @Autowired
    GroupeRepository groupeRepository ;
    public void saveGroupe(Groupe Groupe){
        groupeRepository.save(Groupe);
    }
    public void updateGroupe(Groupe a){
        groupeRepository.save(a);
    }
    public List<Groupe> findAllGroupes() {
        return  groupeRepository.findAll();
    }
    public Groupe findById(int id) {
        return groupeRepository.findById(id).orElse(null);
    }
    public boolean deleteGroupe(Groupe e) {
        try {
            groupeRepository.delete(e);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
