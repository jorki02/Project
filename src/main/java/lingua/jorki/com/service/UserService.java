package lingua.jorki.com.service;

import lingua.jorki.com.model.User;

/**
 * Service class for {@link lingua.jorki.com.model.User}
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
