package lingua.jorki.com.dao;

import lingua.jorki.com.model.Progress;
import lingua.jorki.com.model.User;
import lingua.jorki.com.model.Word;

/**
 * Created by Саша on 08.03.2017.
 */
public interface ProgressDao {
    Progress getProgressByWordAndUser(Word word, User user);
}
