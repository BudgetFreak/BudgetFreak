package de.budgetfreak.budgeting.domain;

import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDataService {

    private MasterCategoryRepository masterCategoryRepository;

    private UserRepository userRepository;

    @Autowired
    public TestDataService(MasterCategoryRepository masterCategoryRepository, UserRepository userRepository) {
        this.masterCategoryRepository = masterCategoryRepository;
        this.userRepository = userRepository;
    }

    public MasterCategory createMasterCategory(User user) {
        return masterCategoryRepository.saveAndFlush(new MasterCategory().setName("name").setDescription("description").setUser(user));
    }

    public User createUser() {
        return userRepository.saveAndFlush(new User().setName("Bob").setCurrency("€"));
    }
}
