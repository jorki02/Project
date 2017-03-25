package lingua.jorki.com.JDBC;

import lingua.jorki.com.modelJDBC.Progress;
import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.Word_Progress;
import lingua.jorki.com.modelJDBC.helper.WordTranslation;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Саша on 08.03.2017.
 */
public interface JDBCService {

    List<Progress> getProgressByUserAndWord(User user, Long wordId);

    List<Word> getWordListByUser(User user);

    List<Word> getWordsByEnglish(String english);

    void addWordToUser(Long wordId, User user);

    void addWord(Word word);

    void deleteWord(User user, Long id);

    List<Word> findWords(User user, String partOFWord);

    List<Word> getUnexploredWords(User user);

    Long getCountRepeat(User user, Long id);

    void setRepeatDate(User user, Long id, Long count, String repeatDate);

    Long getRemainingWordsOfWT(User user);

    Long getRemainingWordsOfTW(User user);

    Long getRemainingWordsOfCards(User user);

    List<Word> getUnexploredWordsOfTranslation(User user);

    List<Word> getHeapOfWords();

    Long getCountRepeatTranslation(User user, Long id);

    void setRepeatDateTranslation(User user, Long id, Long count, String repeatDate);

    List<Word_Progress> getProgress(User user);

    List<Word> getUnexploredWordsOfWords(User user);

    Long getCountRepeatWords(User user, Long id);

    void setRepeatDateWords(User user, Long id, Long count, String repeatDate);
}
