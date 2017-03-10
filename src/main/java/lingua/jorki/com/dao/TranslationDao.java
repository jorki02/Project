package lingua.jorki.com.dao;

import lingua.jorki.com.model.Translation;
import lingua.jorki.com.model.User;
import lingua.jorki.com.model.Word;

import java.util.List;

/**
 * Created by Саша on 07.03.2017.
 */
public interface TranslationDao {

    Translation getTranslationByWordAndUser(Word word, User user);

    List<Translation> getTranslationsByWord(Word word);

}
