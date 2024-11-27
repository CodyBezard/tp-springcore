package fr.diginamic.hello.repository;

import fr.diginamic.hello.objets.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VilleRepo extends JpaRepository<Ville, Integer> {
    //Methode pour la pagination de la methode d'extraction all
    Page<Ville> findAll(Pageable pageable);

    Ville findByName(String name);

    // Recherche de toutes les villes dont le nom commence par une chaine de caractère
//    @Query("SELECT v FROM Ville v WHERE v.name LIKE :prefix%")
    List<Ville> findByNameContains(String prefix);

    //Recherche de toutes les villes dont la population est supérieure à minimum
    @Query("Select v from Ville v Where v.nbHabitants > :param")
    List<Ville> findByNbHabitantsMin(@Param("param") int param);

    //Recherche de toutes les villes dont la population est entre un min et un max
    @Query("Select v from Ville v where v.nbHabitants between :min AND :max")
    List<Ville> findByNbHabitantsBetween(@Param("min") int min, @Param("max") int max);

    //Recherche de toutes les villes d'un département dont la population est supérieur à minimum
    @Query("select v from Ville v Join v.departement d where d.code = :codeDep and v.nbHabitants > :min ")
    List<Ville> findByCodeDepAndPopGreaterThan(@Param("codeDep") String codeDep, @Param("min") int min);

    //Recherche de toutes les villes d'un département dont la population est entre un min et un max
    @Query("select v from Ville v Join v.departement d where d.code = :codeDep and (v.nbHabitants between :min and : max)")
    List<Ville> findByCodeDepAndPopBetween(@Param("codeDep") String codeDep, @Param("min") int min, @Param("max") int max);

    //Recherche des n villes les plus peuplées d'un département donné
    //@Query("select v from Ville v join v.departement d where d.code = :codeDep order by v.nbHabitants DESC ")
    Page<Ville> findByDepartementCodeOrderByNbHabitantsDesc ( String depCode, Pageable pageable);

}
