package geiffel.da4.issuetracker.commentaire;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
public class CommentaireJPAService implements CommentaireService{

    private String strCom = "Commentaire";

    @Autowired
    CommentaireRepository commentaireRepository;
    @Override
    public List<Commentaire> getAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getById(Long id) {
        Optional<Commentaire> commentaire = commentaireRepository.findById(id);
        if (commentaire.isPresent()){
            return commentaire.get();
        }
        throw new ResourceNotFoundException(strCom, id);
    }

    @Override
    public List<Commentaire> getAllByAuthorId(Long id) {
        return commentaireRepository.findAllByAuthorId(id);
    }

    @Override
    public List<Commentaire> getAllByIssueCode(Long code) {
        return commentaireRepository.findAllByIssueCode(code);
    }

    @Override
    public Commentaire create(Commentaire newCommentaire) throws ResourceAlreadyExistsException {
        if (commentaireRepository.existsById(newCommentaire.getId())){
            throw new ResourceAlreadyExistsException(strCom, newCommentaire.getId());
        }
        return commentaireRepository.save(newCommentaire);
    }

    @Override
    public void update(Long id, Commentaire toUpdate) throws ResourceNotFoundException{
        if (commentaireRepository.existsById(id)){
            commentaireRepository.save(toUpdate);
        }
        throw new ResourceNotFoundException(strCom, id);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException{
        if (commentaireRepository.existsById(id)){
            commentaireRepository.deleteById(id);
        }
        throw new ResourceNotFoundException(strCom, id);
    }
}
