package com.example.gestiondents.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String userName ;
    private String password ;
    private String firstName ;
    private String lastName ;
    @Lob
    @Column(length = 1000000)
    private byte[] image;
    @Transient
    private MultipartFile imageFile;

}
