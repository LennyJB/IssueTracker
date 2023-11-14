package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjetLocalServiceTest {
    private List<Projet> projets = new ArrayList<>();

    private ProjetService projetService;

    @BeforeEach
    void setUp() {
        projets.add(new Projet(1L, "unNom1"));
        projets.add(new Projet(2L, "unNom2"));
        projets.add(new Projet(3L, "unNom3"));
        projets.add(new Projet(4L, "unNom4"));

        projetService = new ProjetLocalService(projets);
    }

    ;

    @Test
    void whenGettingAll_shouldBeOfSize4() {
        List<Projet> lesProjets = projetService.getAll();
        assertEquals(4, lesProjets.size());
    }

    @Test
    void whenGettingById_shouldReturnProject1() {
        assertEquals(projets.get(0), projetService.getById(1L));
    }

    @Test
    void whenCreate_shouldReturnTheSame() {
        Projet projet1 = new Projet(5L, "unNom5");
        assertEquals(projet1, projetService.create(projet1));
    }

    @Test
    void whenCreate_shouldBeAddedToTheList() {
        Projet projet1 = new Projet(5L, "unNom5");
        assertEquals(projetService.create(projet1), projetService.getById(5L));
    }

    @Test
    void whenCreatingWithSameId_shouldThrowException() {
        Projet projet1 = new Projet(4L, "unNom5");
        assertThrows(ResourceAlreadyExistsException.class, () -> projetService.create(projet1));
    }

    @Test
    void whenUpdating_shouldModifyProject() {
        Projet projectToUpdate = projets.get(2);
        Projet updatedProject = new Projet(projectToUpdate.getId(), "unNouveauNom");
        projetService.update(updatedProject.getId(), updatedProject);
        Projet projectToCompare = projetService.getById(projectToUpdate.getId());
        assertEquals(updatedProject, projectToCompare);
        assertTrue(projetService.getAll().contains(updatedProject));
    }

    @Test
    void whenUpdatingNonExisting_shouldThrowException() {
        Projet projet1 = new Projet(5L, "unNom5");
        assertThrows(ResourceNotFoundException.class, () -> projetService.update(6L, projet1));
    }

    @Test
    void whenDeletingExistingProject_shouldNotBeInProjectsAnymore() {
        Projet projet1 = projets.get(0);
        projetService.delete(projet1.getId());
        assertFalse(projetService.getAll().contains(projet1));
    }

    @Test
    void whenDeletingNonExisting_shouldThrowException() {
        assertThrows(ResourceNotFoundException.class,()-> projetService.delete(projetService.getById(6L).getId()));
    }
}