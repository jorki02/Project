package lingua.jorki.com.service;

import lingua.jorki.com.JDBC.JDBCService;
import lingua.jorki.com.dao.UserDao;
import lingua.jorki.com.dao.WordDao;
import lingua.jorki.com.model.User;

import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.Word_Progress;
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
    public List<Word> getWordListByUser(User user) {
        return jdbcService.getWordListByUser(user);
    }

    @Override
    public List<Word> getWordsByEnglish(String english) {

        List<Word> output = jdbcService.getWordsByEnglish(english);

        if(output != null && output.size() != 0) {
            return jdbcService.getWordsByEnglish(english);
        } else {
            return null;
        }
    }

    @Override
    public void addWordToUser(Long wordId, User user) {
        if(jdbcService.getProgressByUserAndWord(user, wordId).size() == 0) {
            jdbcService.addWordToUser(wordId, user);
        }
    }

    @Override
    public void addListWords(List<Word> words) {
        for(Word word : words){
            jdbcService.addWord(word);
        }
    }

    @Override
    public void addWord(Word word) {
        jdbcService.addWord(word);
    }

    @Override
    public List<Word> deleteWord(User user, Long id) {
        jdbcService.deleteWord(user, id);
        return jdbcService.getWordListByUser(user);
    }

    @Override
    public List<Word> findWords(User user, String partOfWord) {
        return jdbcService.findWords(user, partOfWord);
    }

    @Override
    public List<Word_Progress> getProgressByUser(User user) {
        return jdbcService.getProgress(user);
    }


}
