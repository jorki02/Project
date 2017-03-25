package lingua.jorki.com.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Саша on 07.03.2017.
 */
/*@Entity
@Table(name = "translation")*/
public class Translation {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "russian")
    private String russian;

    @Column(name = "meaning")
    private String meaning;

    @Column(name = "example")
    private String example;

    @ManyToMany(mappedBy = "translations")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
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
    }*/

}
