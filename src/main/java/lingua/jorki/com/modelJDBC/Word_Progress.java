package lingua.jorki.com.modelJDBC;

import java.util.Date;

/**
 * Created by Саша on 12.03.2017.
 */
public class Word_Progress {

    private String word;
    private Long count_repeat;
    private Long count_repeat_translation;
    private Long count_repeat_word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getCount_repeat() {
        return count_repeat;
    }

    public void setCount_repeat(Long count_repeat) {
        this.count_repeat = count_repeat;
    }

    public Long getCount_repeat_translation() {
        return count_repeat_translation;
    }

    public void setCount_repeat_translation(Long count_repeat_translation) {
        this.count_repeat_translation = count_repeat_translation;
    }

    public Long getCount_repeat_word() {
        return count_repeat_word;
    }

    public void setCount_repeat_word(Long count_repeat_word) {
        this.count_repeat_word = count_repeat_word;
    }
}
