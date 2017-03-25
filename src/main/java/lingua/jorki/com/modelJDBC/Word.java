package lingua.jorki.com.modelJDBC;

import lingua.jorki.com.model.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Саша on 07.03.2017.
 */
public class Word {

    private Long id;
    private String english;
    private String russian;
    private String meaning;
    private String example;

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
}
