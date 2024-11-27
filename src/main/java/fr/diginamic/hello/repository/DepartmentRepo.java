package fr.diginamic.hello.repository;

import fr.diginamic.hello.objets.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Departement, Integer> {
    Departement findByCode(String code);
}
