package lingua.jorki.com.service;

import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.Word_Progress;
import lingua.jorki.com.modelJDBC.helper.WordTranslation;

import java.util.List;

public interface WordService{

    List<Word> getWordListByUser(User user);

    List<Word> getWordsByEnglish(String english);

    void addWordToUser(Long wordId, User user);

    void addListWords(List<Word> words);

    void addWord(Word word);

    List<Word> deleteWord(User user, Long id);

    List<Word> findWords(User user, String partOfWord);

    List<Word_Progress> getProgressByUser(User user);

}
