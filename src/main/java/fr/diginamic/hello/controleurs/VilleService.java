package fr.diginamic.hello.controleurs;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VilleService {
    ArrayList<Ville> ville = new ArrayList<>();

    public VilleService() {
        ville.add(new Ville("Nice", 343000));
        ville.add(new Ville("Carcassonne", 47800));
        ville.add(new Ville("Narbonne", 53400));
        ville.add(new Ville("Lyon", 484000));
        ville.add(new Ville("Foix", 9700));
        ville.add(new Ville("Pau", 77200));
        ville.add(new Ville("Marseille", 850700));
        ville.add(new Ville("Tarbes", 40600));
    }

    public void addVille(String nom, int nbHabitant){
        ville.add(new Ville(nom, nbHabitant));
    }
}
