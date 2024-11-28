package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.exception.FunctionalException;
import fr.diginamic.hello.exception.VilleNotFound;
import fr.diginamic.hello.mvc.VilleDto;
import fr.diginamic.hello.mvc.VilleMapper;
import fr.diginamic.hello.objets.Ville;
import fr.diginamic.hello.services.VilleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    // Get - liste des villes
    @GetMapping
    public List<VilleDto> getAllVilles(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Ville> v = villeService.getAllVilles(pageable);
        if(v!=null){
            List<VilleDto> vDto = VilleMapper.toDto(v);
            return vDto;
        }else return null;
    }

    //Get - ville par id
    @GetMapping("/{id}")
    public ResponseEntity<VilleDto> getVilleById(@PathVariable Integer id) {
        Ville v = villeService.findVilleById(id);
        if(v!=null){
            VilleDto vDto = VilleMapper.toDto(v);
            return ResponseEntity.ok(vDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Get - ville par nom
    @GetMapping("/name/{name}")
    public ResponseEntity<VilleDto> getVilleByName(@PathVariable String name) {
        Ville v = villeService.findVilleByName(name);
        if(v!=null){
            VilleDto vDto = VilleMapper.toDto(v);
            return ResponseEntity.ok(vDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Get - Ville par chaine de caractère
    @GetMapping("/namelike/{chaine}")
    public List<Ville> getVillesChaineCarac(@PathVariable String chaine){
        return villeService.findVilleByNameLike(chaine);
    }

    //Get - Ville avec population superieur à un minimum
    @GetMapping("/population/{nbHabitantsMin}")
    public List<Ville> getVillesNbHabitantsGreaterThan(@PathVariable int nbHabitantsMin){
        return villeService.findVilleByNbHabitantMin(nbHabitantsMin);
    }

    //Get - Ville avec population entre deux valeurs
    @GetMapping("/population/{nbHabitantsMin}/{nbHabitantsMax}")
    public List<Ville> getVillesNbHabitantsBetween(@PathVariable int nbHabitantsMin, @PathVariable int nbHabitantsMax){
        return villeService.findVilleByNbHabitantBetween(nbHabitantsMin, nbHabitantsMax);
    }

    //Get - Ville selon departements avec population supérieur à un minimum
    @GetMapping("/dep/{code}/population/{nbHabitantsMin}")
    public List<Ville> getVilleByDepAndNbHabitantsGreaterThan(@PathVariable String code,@PathVariable int nbHabitantsMin){
        return villeService.findByDepAndNbHabitantGreaterThan(code,nbHabitantsMin);
    }

    //Get - Ville selon departements avec population entre deux valeurs
    @GetMapping("/dep/{code}/population/{nbHabitantsMin}/{nbHabitantsMax}")
    public List<Ville> getVilleByDepAndNbHabitantsBetween(@PathVariable String code,@PathVariable int nbHabitantsMin,@PathVariable int nbHabitantsMax){
        return villeService.findByDepAndNbHabitantBetween(code,nbHabitantsMin,nbHabitantsMax);
    }


    //Get - Ville par dep les plus peuplé selon param
    @GetMapping("/dep/{code}/{n}")
    public Page<Ville> getVillesByDepOrderByNbHabitants(@PathVariable String code, @PathVariable Integer n){
        return villeService.findVilleByDep(code,n);
    }

    //Post - Ajouter une nouvelle ville
    @PostMapping
    public Iterable<Ville> addVille(@RequestBody VilleDto ville) throws FunctionalException {
        Ville v = VilleMapper.toBean(ville);
        return villeService.insertVille(v);
    }

    //Put - Modifier une ville existante
    @PutMapping
    public ResponseEntity<VilleDto> updateVille(@RequestBody VilleDto ville) throws VilleNotFound {
        Ville v = VilleMapper.toBean(ville);
        Ville updateVille = villeService.updateVille(v);
        return ResponseEntity.ok(VilleMapper.toDto(updateVille));
    }

    //Delete - Suppression d'une ville par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Iterable<Ville>> deleteVilleById(@PathVariable Integer id) {
        Iterable<Ville> deleteVille = villeService.deleteVille(id);
        return ResponseEntity.ok(deleteVille);
    }

}
