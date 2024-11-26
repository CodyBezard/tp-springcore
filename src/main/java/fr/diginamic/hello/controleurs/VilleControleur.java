package fr.diginamic.hello.controleurs;

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

    @GetMapping
    public List<Ville> getVilles() { return villeService.getAllVilles();}

    @GetMapping("/{id}")
    public Ville getVilleId(@PathVariable Long id) { return villeService.findVilleById(id);}

    @PostMapping
    public ResponseEntity<String> createVille(@RequestBody Ville ville) {
        boolean idExists= villeService.ville.stream().anyMatch(v -> v.getId()== (ville.getId()));
        if(idExists) {
            return new ResponseEntity<>("L'identifiant est déjà utilisé", HttpStatus.CONFLICT);
        }else {
            if (villeService.addVille(ville)) {
                return new ResponseEntity<String>("Ville insérée avec succès !", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("La ville existe déjà !", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@RequestBody Ville ville) {
        if (villeService.updateVille(ville)) {
            return new ResponseEntity<String>("Succès !",HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("La mise à jour a échouée !",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable Long id) {
        if(villeService.deleteVille(id)){
            return new ResponseEntity<>("Ville deleted !", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("La suppression a échouée !",HttpStatus.BAD_REQUEST);
        }
    }
}
