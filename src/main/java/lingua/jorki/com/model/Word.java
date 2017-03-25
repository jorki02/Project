package lingua.jorki.com.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Саша on 07.03.2017.
 */
/*@Entity
@Table(name="words")*/
public class Word {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;

    /*@Column(name = "english")*/
    private String english;

    /*@Column(name = "russian")*/
    private String russian;

    /*@Column(name = "example")*/
    private String example;

    /*@ManyToMany(mappedBy = "words")*/
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
