package lingua.jorki.com.modelJDBC;

import java.util.Date;

/**
 * Created by Саша on 12.03.2017.
 */
public class Progress {

    private Long user_id;
    private Long word_id;
    private Date last_repeat;
    private Integer count_repeat;
    private Date add_date;
    private Date next_repeat;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getWord_id() {
        return word_id;
    }

    public void setWord_id(Long word_id) {
        this.word_id = word_id;
    }

    public Date getLast_repeat() {
        return last_repeat;
    }

    public void setLast_repeat(Date last_repeat) {
        this.last_repeat = last_repeat;
    }

    public Integer getCount_repeat() {
        return count_repeat;
    }

    public void setCount_repeat(Integer count_repeat) {
        this.count_repeat = count_repeat;
    }

    public Date getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }

    public Date getNext_repeat() {
        return next_repeat;
    }

    public void setNext_repeat(Date next_repeat) {
        this.next_repeat = next_repeat;
    }
}
