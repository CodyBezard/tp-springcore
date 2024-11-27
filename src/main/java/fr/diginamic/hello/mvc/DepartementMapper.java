package fr.diginamic.hello.mvc;

import fr.diginamic.hello.objets.Departement;
import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {

    public DepartementDto toDto(Departement departement) {
        DepartementDto dto = new DepartementDto();
        dto.setCodeDep(departement.getCode());
        dto.setNameDep(departement.getName());
        dto.setNbHabitants(departement);
        return dto;
    }

    public Departement toBean(DepartementDto departementDto) {
        Departement departement = new Departement();
        departement.setCode(departementDto.getCodeDep());
        departement.setName(departementDto.getNameDep());
        return departement;

    }
}
