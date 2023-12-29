package com.example.gestiondents.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User{
    private String number ;

    @ManyToOne
    private Groupe studentGrp ;

    @OneToMany(mappedBy = "pk.student")
    private List <StudentPW> student ;
}
