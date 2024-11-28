package fr.diginamic.hello.services;

import fr.diginamic.hello.objets.Departement;
import fr.diginamic.hello.objets.Ville;
import fr.diginamic.hello.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    /**
     * Extrait et retourne tous les départements en base
     *
     * @return liste des départements
     */
    public Iterable<Departement> getAllDepartments() {
        return departmentRepo.findAll();
    }

    /**
     * Extrait un département par son id
     *
     * @param id l'id du département
     * @return le département correspondant, ou null si il n'existe pas
     */
    public Departement findDepById(int id) {
        Optional<Departement> optionalDep = departmentRepo.findById(id);
        if(optionalDep.isPresent()){
            return optionalDep.get();//Retourne l'objet s'il existe
        }else {
            return null;
        }
    }

    /**
     * Extrait une ville par son code
     *
     * @param code le code du département
     * @return le département correspondant ou null si il n'existe pas
     */
    public Optional<Departement> findDepByCode(String code) {
        if(departmentRepo.findByCode(code).isPresent()){
            return Optional.of(departmentRepo.findByCode(code).get());
        }else{
            return null;
        }
    }


    /**
     * Insert un département
     *
     * @param departement objet de type Département
     * @return la liste des départements après insertion
     */
    public Iterable<Departement> insertDep(Departement departement) {
        departmentRepo.save(departement);
        return departmentRepo.findAll();
    }

    /**
     * Update un departement existant
     * @param id id du département à modifier
     * @param departement objet de type departement
     * @return la liste des departements après mise à jour
     */
    public Iterable<Departement> updateDep(int id,Departement departement) {
        Optional<Departement> v=departmentRepo.findById(id);
        if(v.isPresent()){
            departement.setId(id); //Maintenir l'ID existant de la ville
            departmentRepo.save(departement);
        }else{
            return null;
        }
        return departmentRepo.findAll();
    }

    /**
     * Suppression d'un departement existant
     * @param id id du departement à supprimer
     * @return la liste des departements après suppression
     */
    public Iterable<Departement> deleteDep(int id) {
        departmentRepo.deleteById(id);
        return departmentRepo.findAll();
    }
}
