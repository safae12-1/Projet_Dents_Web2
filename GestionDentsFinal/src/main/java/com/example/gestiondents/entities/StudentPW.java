package com.example.gestiondents.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPW {
    @EmbeddedId
    StudentPwPrimary pk ;
    private String time;

    @Temporal(TemporalType.DATE)
    private Date date ;

    private Long note;

    @OneToMany(mappedBy = "studentimg")
    private List<Image> imagesList;

}
