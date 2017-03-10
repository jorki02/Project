package lingua.jorki.com.dao;

import lingua.jorki.com.model.User;
import lingua.jorki.com.model.Word;

import java.util.List;

public interface WordDao{

    List<Word> getWordListByUser(User user);

}
