package fr.diginamic.hello.objets;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleDao {

    @PersistenceContext
    private EntityManager em;



    //Récupère toutes les villes
    public List<Ville> getAll() {
        return em.createQuery("SELECT v from Ville v", Ville.class).getResultList();
    }

    //Récupère une ville par ID
    public Ville getById(int id) {
        return em.find(Ville.class, id);
    }

    //Récupère une ville par nom
    public Ville getByName(String name) {
        try{
            return em.createQuery("select v from Ville v where v.nom = :name", Ville.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch(Exception e){
            return null; //retourne null si la ville n'est pas trouvé
        }
    }

    //Insère une nouvelle ville
    @Transactional
    public void insert(Ville ville) {
        em.persist(ville);
    }

    //Modifie une ville existante
    @Transactional
    public void update(Ville ville) {
        em.merge(ville);
    }

    //Supprime une ville par ID
    @Transactional
    public void delete(int idVille) {
        Ville ville = em.find(Ville.class, idVille);
        if(ville != null){
            em.remove(ville);
        }
    }
}
