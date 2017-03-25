package lingua.jorki.com.filter;

import lingua.jorki.com.modelJDBC.Word;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саша on 25.03.2017.
 */
@Component
public class FilterWords {

    public List<Word> filterWords(List<Word> fullList, List<Word> rightWords){
        List<Word> forDelete = new ArrayList<>();

        for(Word word : fullList){
            for(Word checkedWord : rightWords) {
                if (checkedWord.getRussian().equals(word.getRussian()) || checkedWord.getEnglish().equals(word.getEnglish()) ) {
                    forDelete.add(word);
                    break;
                }
            }
        }

        fullList.removeAll(forDelete);

        return fullList;
    }


}
