package geiffel.da4.issuetracker;

import geiffel.da4.issuetracker.commentaire.Commentaire;
import geiffel.da4.issuetracker.commentaire.CommentaireRepository;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueRepository;
import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class IssueTrackerStudentsApplication {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;



    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerStudentsApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUpBDD() {
        return args -> {

            List<User> users = new ArrayList<>();
            User user1 = new User(1L, "Machin", Fonction.USER);
            User user2 = new User(2L, "Chose", Fonction.USER);
            User user3 = new User(3L, "Truc", Fonction.DEVELOPPER);

            users.add(user1);
            users.add(user2);
            users.add(user3);
            userRepository.saveAll(users);

            List<Issue> issues = new ArrayList<>();
            Issue issue1 = new Issue(1L, "blah", "some content1", user1, Timestamp.from(Instant.now()), null);
            Issue issue2 = new Issue(2L, "bleuh", "some content2", user2, Timestamp.from(Instant.now()), null);
            Issue issue3 = new Issue(3L, "blih", "some content3", user3, Timestamp.from(Instant.now()), null);

            issues.add(issue1);
            issues.add(issue2);
            issues.add(issue3);
            issueRepository.saveAll(issues);

            List<Commentaire> commentaires = new ArrayList<>();
            Commentaire commentaire1 = new Commentaire(1L, user1, issue1, "Contenu 1");
            Commentaire commentaire2 = new Commentaire(2L, user1, issue1, "Contenu 2");
            Commentaire commentaire3 = new Commentaire(3L, user2, issue1, "Contenu 3");
            Commentaire commentaire4 = new Commentaire(4L, user2, issue1, "Contenu 4");
            Commentaire commentaire5 = new Commentaire(5L, user2, issue2, "Contenu 5");

            commentaires.add(commentaire1);
            commentaires.add(commentaire2);
            commentaires.add(commentaire3);
            commentaires.add(commentaire4);
            commentaires.add(commentaire5);
            commentaireRepository.saveAll(commentaires);
        };
    }
}
