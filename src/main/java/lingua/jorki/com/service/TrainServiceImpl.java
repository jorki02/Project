package lingua.jorki.com.service;

import lingua.jorki.com.JDBC.JDBCService;
import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Саша on 18.03.2017.
 */
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    JDBCService jdbcService;

    @Override
    public List<Word> getUnexploredWords(User user) {

        List<Word> unexploredWords = jdbcService.getUnexploredWords(user);

        if(unexploredWords.size() > 20){
            return unexploredWords.subList(0, 19);
        } else {
            return unexploredWords;
        }
    }

    @Override
    public Long getCountRepeat(User user, Long id) {
        Long response = jdbcService.getCountRepeat(user, id);
        return response;
    }

    @Override
    public void setRepeatDate(User user, Long id, Long count) {
        String repeatDate = "";

        switch(count.intValue()) {
            case 0: repeatDate = "10 minutes";
                break;
            case 1: repeatDate = "1 day";
                break;
            case 2: repeatDate = "3 days";
                break;
            case 3: repeatDate = "7 days";
                break;
            case 4: repeatDate = "14 days";
                break;
            case 5: repeatDate = "30 days";
        }

        System.out.println(id + " " + count + " " + repeatDate);

        jdbcService.setRepeatDate(user, id, count, repeatDate);
    }

    @Override
    public Long countRemainingWords(String type, User user) {
        switch(type) {
            case "wt": return jdbcService.getRemainingWordsOfWT(user);
            case "tw": return jdbcService.getRemainingWordsOfTW(user);
            case "cards": return jdbcService.getRemainingWordsOfCards(user);
        }

        return null;
    }

}
