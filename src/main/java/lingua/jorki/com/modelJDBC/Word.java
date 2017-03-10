package lingua.jorki.com.modelJDBC;

import lingua.jorki.com.model.User;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Саша on 07.03.2017.
 */
public class Word {

    private Long englishId;
    private String english;

    public Long getEnglishId() {
        return englishId;
    }

    public void setEnglishId(Long englishId) {
        this.englishId = englishId;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

}
