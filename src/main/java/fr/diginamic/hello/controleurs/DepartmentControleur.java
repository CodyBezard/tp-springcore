package fr.diginamic.hello.controleurs;


import fr.diginamic.hello.objets.Departement;
import fr.diginamic.hello.objets.Ville;
import fr.diginamic.hello.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departement")
public class DepartmentControleur {
    @Autowired
    private DepartmentService departmentService;

    // Get - liste des departements
    @GetMapping
    public Iterable<Departement> getAllDepartements() {
        return departmentService.getAllDepartments();
    }

    //Get - departement par id
    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepById(@PathVariable Integer id) {
        Departement d = departmentService.findDepById(id);
        if(d!=null){
            return ResponseEntity.ok(d);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Get - departement par code
    @GetMapping("/code/{code}")
    public ResponseEntity<Departement> getDepByName(@PathVariable String code) {
        Departement d = departmentService.findDepByCode(code);
        if(d!=null){
            return ResponseEntity.ok(d);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Post - Ajouter un nouveau departement
    @PostMapping
    public Iterable<Departement> addDep(@RequestBody Departement dep) {
        return departmentService.insertDep(dep);
    }

    //Put - Modifier un departement existant
    @PutMapping("/{id}")
    public ResponseEntity<Iterable<Departement>> updateDep(@PathVariable Integer id, @RequestBody Departement dep) {
        Iterable<Departement> updateDep = departmentService.updateDep(id, dep);
        return ResponseEntity.ok(updateDep);
    }

    //Delete - Suppression d'un departement par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Iterable<Departement>> deleteDepById(@PathVariable Integer id) {
        Iterable<Departement> deleteDep = departmentService.deleteDep(id);
        return ResponseEntity.ok(deleteDep);
    }
}
