package fr.diginamic.hello.mvc;

import fr.diginamic.hello.objets.Departement;
import fr.diginamic.hello.objets.Ville;
import org.springframework.stereotype.Component;

@Component
public class VilleMapper {

    public VilleDto toDto(Ville ville) {
        VilleDto dto = new VilleDto();
        dto.setId(ville.getId());
        dto.setNbHabitants(ville.getNbHabitants());
        dto.setCodeDep(ville.getDepartementCode());
        dto.setNameDep(ville.getDepartementName());
        return dto;
    }

    public Ville toBean(VilleDto dto) {
        Ville bean = new Ville();
        Departement departement = new Departement();
        bean.setId(dto.getId());
        departement.setCode(dto.getCodeDep());
        departement.setName(dto.getNameDep());
        bean.setNbHabitants(dto.getNbHabitants());
        bean.setDepartement(departement);
        return bean;
    }
}
