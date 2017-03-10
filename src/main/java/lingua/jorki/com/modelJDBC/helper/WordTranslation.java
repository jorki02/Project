package lingua.jorki.com.modelJDBC.helper;

import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.modelJDBC.Word;

/**
 * Created by Саша on 09.03.2017.
 */
public class WordTranslation {

    private Long english_id;

    private Long translation_id;

    private String english;

    private String russian;

    private String meaning;

    private String example;

    public WordTranslation() {
    }

    WordTranslation(Word word, Translation translation){
        this.english = word.getEnglish();
        this.russian = translation.getRussian();
        this.meaning = translation.getMeaning();
        this.example = translation.getExample();
    }

    public Long getEnglish_id() {
        return english_id;
    }

    public void setEnglish_id(Long english_id) {
        this.english_id = english_id;
    }

    public Long getTranslation_id() {
        return translation_id;
    }

    public void setTranslation_id(Long translation_id) {
        this.translation_id = translation_id;
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
