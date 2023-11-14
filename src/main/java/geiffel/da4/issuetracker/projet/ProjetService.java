package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ProjetService {
    List<Projet> getAll();

    Projet getById(Long id);

    Projet create(Projet projet);

    void update(Long id, Projet projet);

    void delete(Long id);
}
