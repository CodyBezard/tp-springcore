package fr.diginamic.hello.services;

import fr.diginamic.hello.objets.Ville;
import fr.diginamic.hello.repository.VilleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleService {

    @Autowired
    private VilleRepo villeRepo;


    /**
     * Extrait et retourne toutes les villes en base
     *
     * @return liste des villes
     */
    public Page<Ville> getAllVilles(Pageable pageable) {
        return villeRepo.findAll(pageable);
    }

    /**
     * Extrait une ville par son id
     *
     * @param id l'id de la ville
     * @return la ville correspondante, ou null si elle n'existe pas
     */
    public Ville findVilleById(int id) {
        Optional<Ville> optionalVille = villeRepo.findById(id);
        if(optionalVille.isPresent()){
            return optionalVille.get();//Retourne l'objet s'il existe
        }else {
            return null;
        }
    }

    /**
     * Extrait une ville par son nom
     *
     * @param nom le nom de la ville
     * @return la ville correspondante ou null si elle n'existe pas
     */
    public Ville findVilleByName(String nom) {
        return villeRepo.findByName(nom);
    }

    /**
     * Extrait liste de ville selon chaine de caractère
     * @param nom la chaine de caractère
     * @return la liste de ville correspondante
     */
    public List<Ville> findVilleByNameLike(String nom) {
        return villeRepo.findByNameContains(nom);
    }

    /**
     * Extrait liste ville avec nbHabitant supérieur à param
     * @param nbHabitant nbHabitant
     * @return liste correspondante de ville avec plus de nbHabitant que param
     */
    public List<Ville> findVilleByNbHabitantMin(int nbHabitant) {
        return villeRepo.findByNbHabitantsMin(nbHabitant);
    }

    /**
     * Extrait liste ville dont population entre nbhabitantmin et nbhabitantmax
     * @param nbHabitantMin
     * @param nbHabitantMax
     * @return liste correspondante de ville dont la population est entre les params
     */
    public List<Ville> findVilleByNbHabitantBetween(int nbHabitantMin, int nbHabitantMax) {
        return villeRepo.findByNbHabitantsBetween(nbHabitantMin, nbHabitantMax);
    }

    /**
     * Extrait liste ville par departement avec population supérieur à param
     * @param code code département
     * @param nbHabitantmin nombre d'habitant minimum
     * @return liste correspondante
     */
    public List<Ville> findByDepAndNbHabitantGreaterThan(String code, int nbHabitantmin){
        return villeRepo.findByCodeDepAndPopGreaterThan(code, nbHabitantmin);
    }

    /**
     *
     * @param code Code departement
     * @param nbHabitantmin nombre d'habitant minimal
     * @param nbHabitantmax nombre d'habitant maximal
     * @return liste correspondante
     */
    public List<Ville> findByDepAndNbHabitantBetween(String code, int nbHabitantmin, int nbHabitantmax){
        return villeRepo.findByCodeDepAndPopBetween(code, nbHabitantmin, nbHabitantmax);
    }

    /**
     * Extrait les n ville les plus peuplé d'un departement donné
     * @param code le code département
     * @param n le nombre de résultat désiré
     * @return la liste des villes du département trié par nombre d'habitants
     */
    public Page<Ville> findVilleByDep (String code, Integer n){
        Pageable pageable = PageRequest.of(0, n);
        return villeRepo.findByDepartementCodeOrderByNbHabitantsDesc(code, pageable);
    }

    /**
     * Insert une ville
     *
     * @param ville objet de type Ville
     * @return la liste des villes après insertion
     */
    public Iterable<Ville> insertVille(Ville ville) {
        villeRepo.save(ville);
        return villeRepo.findAll();
    }

    /**
     * Update une ville existante
     * @param id id de la ville à modifier
     * @param ville objet de type ville
     * @return la liste des villes après mise à jour
     */
    public Iterable<Ville> updateVille(int id,Ville ville) {
        Optional<Ville> v=villeRepo.findById(id);
        if(v.isPresent()){
            ville.setId(id); //Maintenir l'ID existant de la ville
            villeRepo.save(ville);
        }else{
            return null;
        }
        return villeRepo.findAll();
    }

    /**
     * Suppression d'une ville existante
     * @param id id de la ville à supprimer
     * @return la liste des villes après suppression
     */
    public Iterable<Ville> deleteVille(int id) {
        villeRepo.deleteById(id);
        return villeRepo.findAll();
    }

}