package com.example.gestiondents.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String objectif;
   private String docs; // Change the type to byte[]

    @ManyToOne
    private Tooth toothPw;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "pw_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Groupe> groupPw;

    @OneToMany(mappedBy = "pk.pw")
    private List<StudentPW> pw1;

    @Override
    public String toString() {
        return "PW{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", objectif='" + objectif + '\'' +
                ", docs='" + docs + '\'' +
                '}';
    }
}
