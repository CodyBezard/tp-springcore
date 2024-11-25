package fr.diginamic.hello.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Ville> getVilles() {
        List<Ville> villes = villeService.ville;
        return villes;
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville ville) {
        for(Ville v : villeService.ville) {
            if(v.getNom().equals(ville.getNom())) {
                return ResponseEntity.badRequest().body("La ville existe déjà");
            }
        }
        villeService.ville.add(ville);
        return ResponseEntity.ok("Ville insérée avec succès");
    }
}
