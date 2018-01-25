package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.user.domain.User;
import de.budgetfreak.budgetfreakapplication.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing users.
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Lists all users in no given order.
     */
    public List<User> list() {
        return userRepository.findAll();
    }

    /**
     * Creates and saves an user.
     *
     * @return The created entity.
     */
    public User create(String name, String currency) {
        final User user = new User(name, currency);
        return userRepository.save(user);
    }
}