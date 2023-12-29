package com.example.gestiondents.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(length = 10000000)
    private byte[] image;

    @Transient
    private MultipartFile imageFile;

    private Long alpha1;
    private Long alpha2;
    private Long alpha3;
    private Long beta1;
    private Long beta2;
    private Long beta3;

    @ManyToOne
    private StudentPW studentimg;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", alpha1=" + alpha1 +
                ", alpha2=" + alpha2 +
                ", alpha3=" + alpha3 +
                ", beta1=" + beta1 +
                ", beta2=" + beta2 +
                ", beta3=" + beta3 +
                '}';
    }
}
