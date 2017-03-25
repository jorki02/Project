package lingua.jorki.com.service;

import lingua.jorki.com.JDBC.JDBCService;
import lingua.jorki.com.filter.FilterWords;
import lingua.jorki.com.model.User;
import lingua.jorki.com.modelJDBC.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Саша on 25.03.2017.
 */
@Service
public class TrainTranslationServiceImpl implements TrainTranslationService {

    @Autowired
    JDBCService jdbcService;

    @Autowired
    FilterWords filterWords;

    private List<Word> wrongWords;

    @Override
    public List<Word> getUnexploredWords(User user) {

        List<Word> rightWords = jdbcService.getUnexploredWordsOfTranslation(user);
        wrongWords = jdbcService.getWordListByUser(user);
        System.out.println(wrongWords.size());

        wrongWords = filterWords.filterWords(wrongWords, rightWords);

        System.out.println(wrongWords.size());

        if(wrongWords.size() < 100){
            wrongWords.addAll(jdbcService.getHeapOfWords());
            wrongWords = filterWords.filterWords(wrongWords, rightWords);
        }

        System.out.println(wrongWords.size());

        if(rightWords.size() > 10){
            return rightWords.subList(0, 9);
        } else {
            return rightWords;
        }
    }

    @Override
    public List<Word> getWrongWords() {
        return wrongWords;
    }

    @Override
    public Long getCountRepeat(User user, Long id) {

        Long response = jdbcService.getCountRepeatTranslation(user, id);
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

        jdbcService.setRepeatDateTranslation(user, id, count, repeatDate);
    }
}
