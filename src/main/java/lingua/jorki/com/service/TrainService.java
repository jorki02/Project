package lingua.jorki.com.service;

import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;

import java.util.List;
import java.util.Set;

/**
 * Created by Саша on 18.03.2017.
 */
public interface TrainService {

    List<Word> getUnexploredWords(User user);

    Long getCountRepeat(User user, Long id);

    void setRepeatDate(User user, Long id, Long count);

    Long countRemainingWords(String type, User user);

}
