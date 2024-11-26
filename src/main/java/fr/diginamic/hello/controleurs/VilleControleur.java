package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.objets.Ville;
import fr.diginamic.hello.services.VilleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    // Get - liste des villes
    @GetMapping
    public List<Ville> getAllVilles() {
        return villeService.getAllVilles();
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
    @GetMapping("/nom/{name}")
    public ResponseEntity<Ville> getVilleByName(@PathVariable String name) {
        Ville v = villeService.findVilleByName(name);
        if(v!=null){
            return ResponseEntity.ok(v);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Post - Ajouter une nouvelle ville
    @PostMapping
    public List<Ville> addVille(@RequestBody Ville ville) {
        return villeService.insertVille(ville);
    }

    //Put - Modifier une ville existante
    @PutMapping("/{id}")
    public ResponseEntity<List<Ville>> updateVille(@PathVariable Integer id, @RequestBody Ville ville) {
        List<Ville> updateVille = villeService.updateVille(id, ville);
        return ResponseEntity.ok(updateVille);
    }

    //Delete - Suppression d'une ville par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Ville>> deleteVilleById(@PathVariable Integer id) {
        List<Ville> deleteVille = villeService.deleteVille(id);
        return ResponseEntity.ok(deleteVille);
    }

}
