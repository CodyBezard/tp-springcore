package fr.diginamic.hello.mvc;

import fr.diginamic.hello.objets.Departement;
import fr.diginamic.hello.objets.Ville;

public class DepartementDto {
    private String codeDep;
    private String nameDep;
    private int nbHabitants;

    public String getCodeDep() {
        return codeDep;
    }

    public void setCodeDep(String codeDep) {
        this.codeDep = codeDep;
    }

    public String getNameDep() {
        return nameDep;
    }

    public void setNameDep(String nameDep) {
        this.nameDep = nameDep;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(Departement departement) {
        if(departement != null && departement.getVilles() != null) {
            int totalHabitants = departement.getVilles().stream().mapToInt(Ville::getNbHabitants).sum();
            this.nbHabitants = totalHabitants;
        }else{
            this.nbHabitants = 0;
        }
    }
}
