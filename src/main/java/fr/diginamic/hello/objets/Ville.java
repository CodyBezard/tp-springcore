package fr.diginamic.hello.objets;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="ville")
public class Ville implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID")
    private int id;
    @Column(name="NOM",nullable = false,unique = true)
    private String nom;
    @Column(name="NBHABITANTS",nullable = false)
    private int nbHabitants;

    public Ville() {}

    public Ville(String nom, int nbHabitants) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ville{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", nbHabitants=").append(nbHabitants);
        sb.append('}');
        return sb.toString();
    }
}
