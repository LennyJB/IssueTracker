package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
public class IssueJPAService implements IssueService{

    @Autowired
    IssueRepository issueRepository;
    @Override
    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    @Override
    public Issue getByCode(Long code) {
        Optional<Issue> issue = issueRepository.findById(code);
        if(issue.isPresent()){
            return issue.get();
        }
        throw new ResourceNotFoundException("Issue", code);

    }

    @Override
    public Issue create(Issue newIssue) throws ResourceAlreadyExistsException {
        if (issueRepository.existsById(newIssue.getCode())){
            throw new ResourceAlreadyExistsException("Issue", newIssue.getCode());
        }
        return issueRepository.save(newIssue);
    }

    @Override
    public void update(Long code, Issue updatedIssue) throws ResourceNotFoundException{
        if (issueRepository.existsById(code)){
            issueRepository.save(updatedIssue);
        }
        throw new ResourceNotFoundException("Issue", code);
    }

    @Override
    public void delete(Long code) throws ResourceNotFoundException{
        if (issueRepository.existsById(code)){
            issueRepository.deleteById(code);
        }
        throw new ResourceNotFoundException("Issue", code);
    }
}
