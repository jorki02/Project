package lingua.jorki.com.service;

import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;

import java.util.List;

/**
 * Created by Саша on 25.03.2017.
 */
public interface TrainWordsService {

    List<Word> getUnexploredWords(User user);

    List<Word> getWrongWords();

    Long getCountRepeat(User user, Long id);

    void setRepeatDate(User user, Long id, Long count);
}
