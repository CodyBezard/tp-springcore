package fr.diginamic.hello.services;

import fr.diginamic.hello.objets.Ville;
import fr.diginamic.hello.objets.VilleDao;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    private VilleDao villeDao;

    @PostConstruct
    public void init(){
        villeDao.insert(new Ville("Nice", 343000));
        villeDao.insert(new Ville("Carcassonne", 47800));
        villeDao.insert(new Ville("Narbonne", 53400));
        villeDao.insert(new Ville("Lyon", 484000));
        villeDao.insert(new Ville("Foix", 9700));
        villeDao.insert(new Ville("Pau", 77200));
        villeDao.insert(new Ville("Marseille", 850700));
        villeDao.insert(new Ville("Tarbes", 40600));
    }

    /**
     * Extrait et retourne toutes les villes en base
     *
     * @return liste des villes
     */
    public List<Ville> getAllVilles() {
        return villeDao.getAll();
    }

    /**
     * Extrait une ville par son id
     *
     * @param id l'id de la ville
     * @return la ville correspondante, ou null si elle n'existe pas
     */
    public Ville findVilleById(int id) {
        return villeDao.getById(id);
    }

    /**
     * Extrait une ville par son nom
     *
     * @param nom le nom de la ville
     * @return la ville correspondante ou null si elle n'existe pas
     */
    public Ville findVilleByName(String nom) {
        return villeDao.getByName(nom);
    }


    /**
     * Insert une ville
     *
     * @param ville objet de type Ville
     * @return la liste des villes après insertion
     */
    public List<Ville> insertVille(Ville ville) {
        villeDao.insert(ville);
        return villeDao.getAll();
    }

    /**
     * Update une ville existante
     * @param id id de la ville à modifier
     * @param ville objet de type ville
     * @return la liste des villes après mise à jour
     */
    public List<Ville> updateVille(int id,Ville ville) {
        Ville v=villeDao.getById(id);
        if(v!=null){
            ville.setId(id); //Maintenir l'ID existant de la ville
            villeDao.update(ville);
        }
        return villeDao.getAll();
    }

    /**
     * Suppression d'une ville existante
     * @param id id de la ville à supprimer
     * @return la liste des villes après suppression
     */
    public List<Ville> deleteVille(int id) {
        villeDao.delete(id);
        return villeDao.getAll();
    }

}