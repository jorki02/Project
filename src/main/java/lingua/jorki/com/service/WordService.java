package lingua.jorki.com.service;

import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.helper.WordTranslation;

import java.util.List;

public interface WordService{

    void addWord(Word word);

    void addWord(Word word, User user);

    List<WordTranslation> getWordListByUser(User user);

    List<WordTranslation> getTranslationsByWord(String word);

    void addWordWithTranslationToUser(Long wordId, Long translationId, User user);

    Word findByEnglishWord(String english);

}
