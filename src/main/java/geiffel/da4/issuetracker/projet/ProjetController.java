package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.utils.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("projets")
public class ProjetController {
    ProjetService projetService;

    @Autowired
    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping("")
    public List<Projet> getAll() {
        return projetService.getAll();
    }

    @GetMapping("/{id}")
    public Projet getById(Long id) {
        return projetService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity create(Projet projet) {
            projetService.create(projet);
            return ResponseEntity.created(URI.create("/users/"+projet.getId().toString())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(Long id, Projet projet) throws ResourceNotFoundException{
        projetService.update(id, projet);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(Long id) throws ResourceNotFoundException{
        projetService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
