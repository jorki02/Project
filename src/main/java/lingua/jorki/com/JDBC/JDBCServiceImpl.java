package lingua.jorki.com.JDBC;

import lingua.jorki.com.modelJDBC.Progress;
import lingua.jorki.com.modelJDBC.Translation;
import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import lingua.jorki.com.modelJDBC.Word_Progress;
import lingua.jorki.com.modelJDBC.helper.WordTranslation;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Саша on 08.03.2017.
 */
public class JDBCServiceImpl implements JDBCService {

    private JdbcTemplate jdbcTemplate;

    public JDBCServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Progress> getProgressByUserAndWord(User user, Long wordId) {
        String sql = "SELECT p.id, p.word_id, p.user_id, p.last_repeat, p.count_repeat\n" +
                "FROM progress AS p\n" +
                "WHERE p.word_id = ? AND p.user_id = ?;";

        return jdbcTemplate.query(sql, new Integer[]{Integer.parseInt(String.valueOf(wordId)),
                        Integer.parseInt(String.valueOf(user.getId()))},
                new BeanPropertyRowMapper<Progress>(Progress.class));
    }

    @Override
    public List<Word> getWordListByUser(User user) {

        String sql = "SELECT w.id, w.english, w.russian, w.meaning, w.example\n" +
                "FROM words AS w, progress AS p\n" +
                "WHERE w.id = p.word_id AND p.user_id = ?\n" +
                "ORDER BY p.add_date DESC;";

        return jdbcTemplate.query(sql, new Integer[]{Integer.parseInt(String.valueOf(user.getId()))},
                new BeanPropertyRowMapper<Word>(Word.class));

    }

    @Override
    public List<Word> getWordsByEnglish(String english) {
        String sql = "SELECT w.id, w.english, w.russian, w.meaning, w.example\n" +
                "FROM words AS w\n" +
                "WHERE w.english = ?;";

        return jdbcTemplate.query(sql, new String[]{english},
                new BeanPropertyRowMapper<Word>(Word.class));

    }


    @Override
    public void addWordToUser(Long wordId, User user) {
        String sql1 = "INSERT INTO progress(\n" +
                "            word_id, user_id, last_repeat, count_repeat, add_date, next_repeat)\n" +
                "    VALUES (?, ?, null, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";
        String sql2 = "INSERT INTO progress_word_translation(\n" +
                "            word_id, user_id, last_repeat, count_repeat, add_date, next_repeat)\n" +
                "    VALUES (?, ?, null, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";
        String sql3 = "INSERT INTO progress_translation_word(\n" +
                "            word_id, user_id, last_repeat, count_repeat, add_date, next_repeat)\n" +
                "    VALUES (?, ?, null, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";

        jdbcTemplate.update(sql1, new Integer[]{Integer.parseInt(String.valueOf(wordId)),
                Integer.parseInt(String.valueOf(user.getId()))});

        jdbcTemplate.update(sql2, new Integer[]{Integer.parseInt(String.valueOf(wordId)),
                Integer.parseInt(String.valueOf(user.getId()))});

        jdbcTemplate.update(sql3, new Integer[]{Integer.parseInt(String.valueOf(wordId)),
                Integer.parseInt(String.valueOf(user.getId()))});

    }

    @Override
    public void addWord(Word word) {
        String sql = "INSERT INTO words(\n" +
                "            english, russian, meaning, example)\n" +
                "    VALUES (?, ?, ?, ?);";

        jdbcTemplate.update(sql, new String[]{word.getEnglish(),
                word.getRussian(), word.getMeaning(), word.getExample()});

    }

    @Override
    public void deleteWord(User user, Long id) {
        String sql1 = "DELETE FROM progress\n" +
                " WHERE word_id = ? AND user_id = ?;";

        String sql2 = "DELETE FROM progress_word_translation\n" +
                " WHERE word_id = ? AND user_id = ?;";

        String sql3 = "DELETE FROM progress_translation_word\n" +
                " WHERE word_id = ? AND user_id = ?;";

        jdbcTemplate.update(sql1, new Integer[]{Integer.parseInt(String.valueOf(id)),
                Integer.parseInt(String.valueOf(user.getId()))});

        jdbcTemplate.update(sql2, new Integer[]{Integer.parseInt(String.valueOf(id)),
                Integer.parseInt(String.valueOf(user.getId()))});

        jdbcTemplate.update(sql3, new Integer[]{Integer.parseInt(String.valueOf(id)),
                Integer.parseInt(String.valueOf(user.getId()))});
    }

    @Override
    public List<Word> findWords(User user, String partOFWord) {
        String sql = "SELECT w.id, w.english, w.russian, w.meaning, w.example\n" +
                "  FROM words w, progress p\n" +
                "  WHERE p.user_id = ? AND w.id = p.word_id AND w.english LIKE ? || '%'";

        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId()), partOFWord},
                new BeanPropertyRowMapper<Word>(Word.class));
    }

    @Override
    public List<Word> getUnexploredWords(User user) {
        String sql = "SELECT w.id, w.english, w.russian, w.meaning, w.example\n" +
                "  FROM words w, progress p\n" +
                "  WHERE p.user_id = ? AND p.next_repeat < CURRENT_TIMESTAMP AND p.word_id = w.id";

        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId())},
                new BeanPropertyRowMapper<Word>(Word.class));
    }

    @Override
    public Long getCountRepeat(User user, Long id) {
        String sql = "SELECT p.count_repeat\n" +
                "  FROM progress p\n" +
                "  WHERE p.user_id = ? AND p.word_id = ?";


        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId()), id.intValue()},
                new ResultSetExtractor<Long>() {
                    @Override
                    public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        resultSet.next();
                        return resultSet.getLong(1);
                    }
                });
    }

    @Override
    public void setRepeatDate(User user, Long id, Long count, String repeatDate) {
        String sql = "UPDATE progress\n" +
                "   SET count_repeat=?, next_repeat=CURRENT_TIMESTAMP + ?::interval \n" +
                " WHERE word_id = ? AND user_id = ?;";

        jdbcTemplate.update(sql, new Object[]{count.intValue(), repeatDate, id.intValue(), user.getId().intValue()});
    }

    @Override
    public Long getRemainingWordsOfWT(User user) {
        String sql = "SELECT COUNT(pwt.*)\n" +
                "  FROM progress_word_translation pwt\n" +
                "  WHERE pwt.user_id = ? AND pwt.next_repeat < CURRENT_TIMESTAMP";

        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId())},
                new ResultSetExtractor<Long>() {
                    @Override
                    public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        resultSet.next();
                        return resultSet.getLong(1);
                    }
                });
    }

    @Override
    public Long getRemainingWordsOfTW(User user) {
        String sql = "SELECT COUNT(ptw.*)\n" +
                "  FROM progress_translation_word ptw\n" +
                "  WHERE ptw.user_id = ? AND ptw.next_repeat < CURRENT_TIMESTAMP";

        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId())},
                new ResultSetExtractor<Long>() {
                    @Override
                    public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        resultSet.next();
                        return resultSet.getLong(1);
                    }
                });
    }

    @Override
    public Long getRemainingWordsOfCards(User user) {
        String sql = "SELECT COUNT(p.*)\n" +
                "  FROM progress p\n" +
                "  WHERE p.user_id = ? AND p.next_repeat < CURRENT_TIMESTAMP";

        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId())},
                new ResultSetExtractor<Long>() {
                    @Override
                    public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        resultSet.next();
                        return resultSet.getLong(1);
                    }
                });
    }

    @Override
    public List<Word> getUnexploredWordsOfTranslation(User user) {
        String sql = "SELECT w.id, w.english, w.russian, w.meaning, w.example\n" +
                "  FROM words w, progress_word_translation pwt\n" +
                "  WHERE pwt.user_id = ? AND pwt.next_repeat < CURRENT_TIMESTAMP AND pwt.word_id = w.id";

        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId())},
                new BeanPropertyRowMapper<Word>(Word.class));
    }

    @Override
    public List<Word> getHeapOfWords() {
        String sql = "SELECT h.english, h.russian\n" +
                "FROM heap_words AS h";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Word>(Word.class));

    }

    @Override
    public Long getCountRepeatTranslation(User user, Long id) {
        String sql = "SELECT pwt.count_repeat\n" +
                "  FROM progress_word_translation pwt\n" +
                "  WHERE pwt.user_id = ? AND pwt.word_id = ?";


        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId()), id.intValue()},
                new ResultSetExtractor<Long>() {
                    @Override
                    public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        resultSet.next();
                        return resultSet.getLong(1);
                    }
                });
    }

    @Override
    public void setRepeatDateTranslation(User user, Long id, Long count, String repeatDate) {
        String sql = "UPDATE progress_word_translation\n" +
                "   SET count_repeat=?, next_repeat=CURRENT_TIMESTAMP + ?::interval \n" +
                " WHERE word_id = ? AND user_id = ?;";

        jdbcTemplate.update(sql, new Object[]{count.intValue(), repeatDate, id.intValue(), user.getId().intValue()});
    }

    @Override
    public List<Word_Progress> getProgress(User user) {
        String sql = "SELECT w.english as word, p.count_repeat, pwt.count_repeat as count_repeat_translation, ptw.count_repeat as count_repeat_word" +
                "  FROM progress p, progress_word_translation pwt, progress_translation_word ptw, words w\n" +
                "  WHERE p.word_id = w.id AND p.user_id = ? AND pwt.word_id = w.id AND pwt.user_id = ? AND ptw.word_id = w.id AND ptw.user_id = ?";

        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId()), Math.toIntExact(user.getId()), Math.toIntExact(user.getId())},
                new BeanPropertyRowMapper<Word_Progress>(Word_Progress.class));
    }

    @Override
    public List<Word> getUnexploredWordsOfWords(User user) {
        String sql = "SELECT w.id, w.english, w.russian, w.meaning, w.example\n" +
                "  FROM words w, progress_translation_word ptw\n" +
                "  WHERE ptw.user_id = ? AND ptw.next_repeat < CURRENT_TIMESTAMP AND ptw.word_id = w.id";

        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId())},
                new BeanPropertyRowMapper<Word>(Word.class));
    }

    @Override
    public Long getCountRepeatWords(User user, Long id) {
        String sql = "SELECT ptw.count_repeat\n" +
                "  FROM progress_translation_word ptw\n" +
                "  WHERE ptw.user_id = ? AND ptw.word_id = ?";


        return jdbcTemplate.query(sql, new Object[]{Math.toIntExact(user.getId()), id.intValue()},
                new ResultSetExtractor<Long>() {
                    @Override
                    public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        resultSet.next();
                        return resultSet.getLong(1);
                    }
                });
    }

    @Override
    public void setRepeatDateWords(User user, Long id, Long count, String repeatDate) {
        String sql = "UPDATE progress_translation_word\n" +
                "   SET count_repeat=?, next_repeat=CURRENT_TIMESTAMP + ?::interval \n" +
                " WHERE word_id = ? AND user_id = ?;";

        jdbcTemplate.update(sql, new Object[]{count.intValue(), repeatDate, id.intValue(), user.getId().intValue()});
    }


}
