package fr.diginamic.hello.controleurs;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Ville> getAllVilles(){ return this.ville;}

    public boolean addVille(Ville ville){
        Ville result = findVilleByName(ville.getNom());
        if(result!=null){
            return false;
        }
        this.ville.add(ville);
        return true;
    }

    public boolean updateVille(Ville ville){
        Ville result= findVilleById(ville.getId());
        if(result!=null){
            result.setNom(ville.getNom());
            result.setNbHabitants(ville.getNbHabitants());
            return true;
        }
        return false;
    }

    public boolean deleteVille(Long id){
        Ville result = findVilleById(id);
        if(result!=null){
            this.ville.remove(result);
            return true;
        }
        return false;
    }



    public Ville findVilleById(Long id){
        Ville result = this.ville.stream().filter(element -> id.equals(element.getId())).findAny().orElse(null);
        return result;
    }

    private Ville findVilleByName(String nom){
        Ville result = this.ville.stream().filter(element -> nom.equals(element.getNom())).findAny().orElse(null);
        return result;
    }
}
