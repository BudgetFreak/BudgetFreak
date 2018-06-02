package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.user.domain.User;
import de.budgetfreak.budgetfreakapplication.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing users.
 */
@Service
public class UserService {

    public final static List<String> CONST_ISSUE = new ArrayList<>();

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        CONST_ISSUE.add("foo");
        CONST_ISSUE.add("bar");
    }

    /**
     * List all users in no given order.
     */
    public List<User> list() {
        return userRepository.findAll();
    }

    /**
     * Create and saves an user.
     *
     * @return The created entity.
     */
    public User create(String name, String currency) {
        final User user = new User(name, currency);
        return userRepository.save(user);
    }

    /**
     * Get one user by id.
     */
    public User get(long id) {
        return userRepository.findOne(id);
    }


//    private void bar() throws Throwable {
//        try {
//            new FileInputStream(new File("foo"));
//        } catch (Throwable e) {
//            throw new Exception(e.getMessage());
//        }
//    }
}
