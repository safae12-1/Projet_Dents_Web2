package com.example.gestiondents.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class StudentPwPrimary implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_student", insertable = false, updatable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "id_pw" , insertable = false, updatable = false)
    private PW pw;
}
