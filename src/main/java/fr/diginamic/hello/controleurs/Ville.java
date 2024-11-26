package fr.diginamic.hello.controleurs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Ville {
    private static Long idCounter = 1L;
    @Min(1)
    private Long id;
    @NotNull
    @Size(min = 2)
    private String nom;
    @Min(1)
    private int nbHabitants;

    public Ville(String nom, int nbHabitants) {
        this.id = idCounter++;
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    public Long getId() {
        return id;
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
