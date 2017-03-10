package lingua.jorki.com.dao.DAOImpl;

import lingua.jorki.com.dao.WordDao;
import lingua.jorki.com.model.User;
import lingua.jorki.com.model.Word;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Саша on 07.03.2017.
 */
@Repository
@Transactional
public class WordDaoImpl implements WordDao{

    private final SessionFactory sessionFactory;

    @Autowired
    public WordDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Word> getWordListByUser(User user) {
        Criteria criteria = sessionFactory.
                getCurrentSession().createCriteria(Word.class);

        criteria.createAlias("dokument.role", "role"); // inner join by default
        criteria.createAlias("role.contact", "contact");
        criteria.add(Restrictions.eq("contact.lastName", "Test"));
        return criteria.list();

    }


}
