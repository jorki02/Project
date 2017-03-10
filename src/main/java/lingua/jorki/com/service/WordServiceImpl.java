package lingua.jorki.com.service;

import lingua.jorki.com.JDBC.JDBCService;
import lingua.jorki.com.dao.UserDao;
import lingua.jorki.com.dao.WordDao;
import lingua.jorki.com.model.User;

import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.helper.WordTranslation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Саша on 07.03.2017.
 */
@Service
public class WordServiceImpl implements WordService{

    @Autowired
    JDBCService jdbcService;

    @Override
    public void addWord(Word word) {

    }

    @Override
    public void addWord(Word word, User user) {

    }

    @Override
    public List<WordTranslation> getWordListByUser(User user) {

        return jdbcService.getWordWithTranslationListByUser(user);

    }

    @Override
    public List<WordTranslation> getTranslationsByWord(String word) {
        return jdbcService.getTranslationsByWord(word);
    }

    @Override
    public void addWordWithTranslationToUser(Long wordId, Long translationId, User user) {
        jdbcService.addWordWithTranslationToUser(wordId, translationId, user);
    }

    @Override
    public Word findByEnglishWord(String english) {
        return null;
    }

}
