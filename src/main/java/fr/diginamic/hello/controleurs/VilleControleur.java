package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.objets.Ville;
import fr.diginamic.hello.services.VilleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    // Get - liste des villes
    @GetMapping
    public Page<Ville> getAllVilles(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return villeService.getAllVilles(pageable);
    }

    //Get - ville par id
    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable Integer id) {
        Ville v = villeService.findVilleById(id);
        if(v!=null){
            return ResponseEntity.ok(v);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Get - ville par nom
    @GetMapping("/name/{name}")
    public ResponseEntity<Ville> getVilleByName(@PathVariable String name) {
        Ville v = villeService.findVilleByName(name);
        if(v!=null){
            return ResponseEntity.ok(v);
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
    public Iterable<Ville> addVille(@RequestBody Ville ville) {
        return villeService.insertVille(ville);
    }

    //Put - Modifier une ville existante
    @PutMapping("/{id}")
    public ResponseEntity<Iterable<Ville>> updateVille(@PathVariable Integer id, @RequestBody Ville ville) {
        Iterable<Ville> updateVille = villeService.updateVille(id, ville);
        return ResponseEntity.ok(updateVille);
    }

    //Delete - Suppression d'une ville par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Iterable<Ville>> deleteVilleById(@PathVariable Integer id) {
        Iterable<Ville> deleteVille = villeService.deleteVille(id);
        return ResponseEntity.ok(deleteVille);
    }

}
