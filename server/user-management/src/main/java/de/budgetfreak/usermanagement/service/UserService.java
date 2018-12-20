package de.budgetfreak.usermanagement.service;

import de.budgetfreak.usermanagement.UserManagementProperties;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing users.
 */
@Service
@EnableConfigurationProperties(UserManagementProperties.class)
public class UserService {

    private UserRepository userRepository;
    private UserManagementProperties userManagementProperties;

    @Autowired
    public UserService(UserRepository userRepository, UserManagementProperties userManagementProperties) {
        this.userRepository = userRepository;
        this.userManagementProperties = userManagementProperties;
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
    public Optional<User> get(long id) {
        return userRepository.findOne(Example.of(new User().setId(id)));
    }

    /**
     * Reads the testProperty.
     *
     * @return The configured property.
     */
    public String getTestProperty() {
        return userManagementProperties.getTestProperty();
    }
}
