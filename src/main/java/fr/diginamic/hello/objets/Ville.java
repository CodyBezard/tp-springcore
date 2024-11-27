package fr.diginamic.hello.objets;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="VILLE")
public class Ville implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID")
    private int id;
    @Column(name="NAME",nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name="DEPARTMENT_ID")
    Departement departement;
    @Column(name="NB_INHABITANTS",nullable = false)
    private int nbHabitants;

    public Ville() {}

    public Ville(String nom, int nbHabitants) {
        this.name = nom;
        this.nbHabitants = nbHabitants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return name;
    }

    public void setNom(String nom) {
        this.name = nom;
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
        sb.append(", nom='").append(name).append('\'');
        sb.append(", nbHabitants=").append(nbHabitants);
        sb.append('}');
        return sb.toString();
    }
}
