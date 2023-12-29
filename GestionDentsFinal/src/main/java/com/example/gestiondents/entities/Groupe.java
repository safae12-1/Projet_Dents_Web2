package com.example.gestiondents.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String code ;
    private String year ;

    @ManyToOne
    private Professor profGrp;

    @OneToMany(mappedBy = "studentGrp")
    private List<Student> grpStdnt ;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<PW> pwGroup;

    @Override
    public String toString() {
        return "Groupe{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", year='" + year + '\'' +
                ", profGrp=" + (profGrp != null ? profGrp.getId() : "null") +
                ", grpStdnt=" + grpStdnt +
                ", pwGroup=" + pwGroup +
                '}';
    }
}
