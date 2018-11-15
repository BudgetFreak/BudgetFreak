package de.budgetfreak.budgeting.domain;

import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
@TestPropertySource(locations = "/application-test.properties")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository testSubject;

    @Autowired
    private MasterCategoryRepository masterCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveNewMaterCategories() {
        User user = createUser();
        MasterCategory masterCategory = createMasterCategory(user);

        Category category = new Category()
                .setName("category")
                .setDescription("description")
                .setMasterCategory(masterCategory)
                .setUser(user);
        Long categoryId = testSubject.save(category).getId();

        Optional<Category> savedCategoryOptional = testSubject.findById(categoryId);
        assertThat(savedCategoryOptional).isPresent();
        Category savedCategory = savedCategoryOptional.get();
        assertThat(savedCategory.getName()).isEqualTo("category");
        assertThat(savedCategory.getDescription()).isEqualTo("description");
        assertThat(savedCategory.getMasterCategory().getId()).isEqualTo(masterCategory.getId());
        assertThat(savedCategory.getType()).describedAs("The default type should be %s but was %s.", CategoryType.USER_DEFINED, savedCategory.getType()).isEqualByComparingTo(CategoryType.USER_DEFINED);
    }

    private MasterCategory createMasterCategory(User user) {
        return masterCategoryRepository.save(new MasterCategory().setName("name").setDescription("description").setUser(user));
    }

    private User createUser() {
        return userRepository.save(new User().setName("Bob").setCurrency("€"));
    }
}