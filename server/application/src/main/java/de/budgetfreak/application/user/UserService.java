package de.budgetfreak.application.user;

import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
        return userRepository.findOne(Example.of(new User().setId(id))).orElse(null);
    }
}
