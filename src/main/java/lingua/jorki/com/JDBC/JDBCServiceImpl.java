package lingua.jorki.com.JDBC;

import lingua.jorki.com.model.Progress;
import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.helper.WordTranslation;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Саша on 08.03.2017.
 */
public class JDBCServiceImpl implements JDBCService {

    private JdbcTemplate jdbcTemplate;

    public JDBCServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Word> getWordListByUser(User user) {

        String sql = "SELECT w.english, \n" +
                "FROM words AS w, user_word_translation_progress AS uwtp\n" +
                "WHERE w.english_id = uwtp.word_id AND uwtp.user_id = ?;";

        return jdbcTemplate.query(sql, new Integer[]{Integer.parseInt(String.valueOf(user.getId()))},
                new BeanPropertyRowMapper<Word>(Word.class));

    }

    @Override
    public List<WordTranslation> getWordWithTranslationListByUser(User user) {
        String sql = "SELECT w.english_id, w.english, t.translation_id, t.russian, t.meaning, t.example\n" +
                "FROM words AS w, user_word_translation_progress AS uwtp, translation AS t\n" +
                "WHERE uwtp.user_id = ? AND uwtp.word_id = w.english_id AND t.translation_id = uwtp.translation_id";

        return jdbcTemplate.query(sql, new Integer[]{Integer.parseInt(String.valueOf(user.getId()))},
                new BeanPropertyRowMapper<WordTranslation>(WordTranslation.class));
    }

    @Override
    public Progress getProgressByWordAndUser(Word word, User user) {
        return null;
    }

    @Override
    public Translation getTranslationByWordAndUser(Word word, User user) {

        String sql = "SELECT t.russian, t.meaning, t.example\n" +
                "FROM user_word_translation_progress AS uwtp, translation AS t\n" +
                "WHERE uwtp.user_id = ? AND uwtp.word_id = ? AND t.translation_id = uwtp.translation_id";

        return jdbcTemplate.query(sql, new Integer[]{Integer.parseInt(String.valueOf(user.getId())), Integer.parseInt(String.valueOf(word.getEnglishId()))},
                new BeanPropertyRowMapper<Translation>(Translation.class)).get(0);
    }

    @Override
    public List<WordTranslation> getTranslationsByWord(String word) {

        String sql = "SELECT w.english_id, w.english, t.translation_id, t.russian, t.meaning, t.example\n" +
                "FROM words AS w, user_word_translation_progress AS uwtp, translation AS t\n" +
                "WHERE w.english = ? AND uwtp.word_id = w.english_id AND t.translation_id = uwtp.translation_id";

        return jdbcTemplate.query(sql, new String[]{word},
                new BeanPropertyRowMapper<WordTranslation>(WordTranslation.class));

    }

    @Override
    public void addWordWithTranslationToUser(Long wordId, Long translationId, User user) {
        String sql = "INSERT INTO user_word_translation_progress VALUES (?, ?, null, ?);";

        jdbcTemplate.update(sql, new Integer[]{Integer.parseInt(String.valueOf(user.getId())),
                Integer.parseInt(String.valueOf(wordId)),
                Integer.parseInt(String.valueOf(translationId))});

    }

}
