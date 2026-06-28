package sn.l2gl.suunu.daara.model.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="talibes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Talibes {
    @Id
    @Column(length=200,unique = true)
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String nomTuteur;
    private String telephoneTuteur;

   /* @ManyToOne(optional = false)
    @JoinColumn(name="classe_code")
    private Classe classe;*/
}
