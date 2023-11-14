package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.LocalService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetLocalService extends LocalService<Projet, Long> implements ProjetService {

    public ProjetLocalService(List<Projet> allValues) {
        super(allValues);
    }

    @Override
    protected String getIdentifier() {
        return "id";
    }

    @Override
    public List<Projet> getAll() {
        return super.getAll();
    }


    @Override
    public Projet getById(Long id) {
        return getByIdentifier(id);
    }

    @Override
    public Projet create(Projet projet) {
        try{
            this.findByProperty(projet.getId());
            throw new ResourceAlreadyExistsException("Projet", projet.getId());
        }
        catch (ResourceNotFoundException e){
            this.allValues.add(projet);
            return projet;
        }
    }

    @Override
    public void update(Long id, Projet projet) throws ResourceNotFoundException{
        IndexAndValue<Projet> projectFound = findById(id);
        this.allValues.remove(projectFound.index());
        this.allValues.add(projectFound.index(), projet);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException{
        IndexAndValue<Projet> projectFound = findById(id);
        this.allValues.remove(projectFound.index());
    }

    public IndexAndValue<Projet> findById(Long id) {
        return super.findByProperty(id);
    }

}
