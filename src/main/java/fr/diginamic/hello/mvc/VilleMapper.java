package fr.diginamic.hello.mvc;

import fr.diginamic.hello.objets.Departement;
import fr.diginamic.hello.objets.Ville;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VilleMapper {

    public static VilleDto toDto(Ville ville) {
        VilleDto dto = new VilleDto();
        dto.setId(ville.getId());
        dto.setName(ville.getName());
        dto.setNbHabitants(ville.getNbHabitants());
        dto.setCodeDep(ville.getDepartementCode());
        dto.setNameDep(ville.getDepartementName());
        return dto;
    }

    public static List<VilleDto> toDto(Page<Ville> villes){
        List<VilleDto> pageDto = new ArrayList<>();
        for(Ville ville : villes){
            pageDto.add(toDto(ville));
        }
        return pageDto;
    }

    public static Ville toBean(VilleDto dto) {
        Ville bean = new Ville();
        Departement departement = new Departement();
        bean.setId(dto.getId());
        bean.setName(dto.getName());
        departement.setCode(dto.getCodeDep());
        departement.setName(dto.getNameDep());
        bean.setNbHabitants(dto.getNbHabitants());
        bean.setDepartement(departement);
        return bean;
    }
}
