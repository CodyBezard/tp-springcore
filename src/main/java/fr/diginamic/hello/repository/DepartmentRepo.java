package fr.diginamic.hello.repository;

import fr.diginamic.hello.objets.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Departement, Integer> {
    Optional<Departement> findByCode(String code);
}
