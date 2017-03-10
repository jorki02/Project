package lingua.jorki.com.modelJDBC;

import lingua.jorki.com.model.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Саша on 07.03.2017.
 */
public class Translation {

    private Long translation_id;

    private String russian;

    private String meaning;

    private String example;

    public Long getTranslation_id() {
        return translation_id;
    }

    public void setTranslation_id(Long translation_id) {
        this.translation_id = translation_id;
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
