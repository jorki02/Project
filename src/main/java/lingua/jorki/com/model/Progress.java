package lingua.jorki.com.model;

import javax.persistence.*;

/**
 * Created by Саша on 08.03.2017.
 */
@Entity
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_repeat")
    private String last_repeat;

    @Column(name = "repeat_times")
    private Integer repeat_times;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progress")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLast_repeat() {
        return last_repeat;
    }

    public void setLast_repeat(String last_repeat) {
        this.last_repeat = last_repeat;
    }

    public Integer getRepeat_times() {
        return repeat_times;
    }

    public void setRepeat_times(Integer repeat_times) {
        this.repeat_times = repeat_times;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
