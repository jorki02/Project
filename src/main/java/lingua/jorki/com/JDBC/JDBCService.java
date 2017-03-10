package lingua.jorki.com.JDBC;

import lingua.jorki.com.model.Progress;
import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.helper.WordTranslation;

import java.util.List;

/**
 * Created by Саша on 08.03.2017.
 */
public interface JDBCService {

    List<Word> getWordListByUser(User user);

    List<WordTranslation> getWordWithTranslationListByUser(User user);

    Progress getProgressByWordAndUser(Word word, User user);

    Translation getTranslationByWordAndUser(Word word, User user);

    List<WordTranslation> getTranslationsByWord(String word);

    void addWordWithTranslationToUser(Long wordId, Long translationId, User user);

}
