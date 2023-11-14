package geiffel.da4.issuetracker.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import geiffel.da4.issuetracker.commentaire.Commentaire;
import geiffel.da4.issuetracker.commentaire.CommentaireURLSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
/*    @NotNull
    @Max(10)
    @Pattern(regexp = "[^[0-9]{1,10}$]")*/
    private Long id;
/*    @NotNull
    @Min(3)@Max(40)
    @Pattern(regexp = "[^[a-z]{3,40}$]")*/
    private String nom;
/*    @NotNull
    @Min(4)@Max(255)
    @Pattern(regexp = "[^[a-z]{2,30}$]")*/
    private Fonction fonction;
    @OneToMany(mappedBy = "author")
    @JsonSerialize(contentUsing = CommentaireURLSerializer.class)
    private List<Commentaire> commentaireEcrits;


    public User() {
    }

    public User(Long id, String nom, Fonction fonction) {
        this.id = id;
        this.nom = nom;
        this.fonction = fonction;
        this.commentaireEcrits= new ArrayList<>();

    }

    public void addCommentaire(Commentaire commentaire){
        this.commentaireEcrits.add(commentaire);

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public List<Commentaire> getCommentaireEcrits() {
        return commentaireEcrits;
    }

    public void setCommentaireEcrits(List<Commentaire> commentaireEcrits) {
        this.commentaireEcrits = commentaireEcrits;
    }


    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()){
            return false;
        }
        User comparing = (User) obj;
        return Objects.equals(this.id, comparing.getId()) &&
            this.nom.equals(comparing.getNom()) &&
            this.fonction == comparing.getFonction();
    }
}
