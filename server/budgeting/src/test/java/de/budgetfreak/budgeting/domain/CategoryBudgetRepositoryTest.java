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
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
public class CategoryBudgetRepositoryTest {

    @Autowired
    private CategoryBudgetRepository testSubject;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MasterCategoryRepository masterCategoryRepository;

    @Test
    public void shouldSaveNewMaterCategories() {
        User user = createUser();
        MasterCategory masterCategory = createMasterCategory(user);
        Category category = createCategory(user, masterCategory);
        Budget budget = budgetRepository.save(new Budget().setUser(user).setYear(2018).setMonth(8));

        CategoryBudget categoryBudget = new CategoryBudget().setBudget(budget).setCategory(category).setAmount(new BigDecimal("1337.42")).setDescription("description");
        Long id = testSubject.save(categoryBudget).getId();

        Optional<CategoryBudget> savedCategoryBudgetOptional = testSubject.findById(id);
        assertThat(savedCategoryBudgetOptional).isPresent();
        CategoryBudget savedCategoryBudget = savedCategoryBudgetOptional.get();
        assertThat(savedCategoryBudget.getDescription()).isEqualTo("description");
        assertThat(savedCategoryBudget.getAmount()).isEqualTo(new BigDecimal("1337.42"));
        assertThat(savedCategoryBudget.getBudget().getId()).isEqualTo(budget.getId());
        assertThat(savedCategoryBudget.getCategory().getId()).isEqualTo(category.getId());
    }

    private Category createCategory(User user, MasterCategory masterCategory) {
        return categoryRepository.save(new Category().setName("category").setMasterCategory(masterCategory).setUser(user));
    }

    private MasterCategory createMasterCategory(User user) {
        return masterCategoryRepository.save(new MasterCategory().setName("mastercategory").setUser(user));
    }

    private User createUser() {
        return userRepository.save(new User().setName("Bob").setCurrency("€"));
    }

    @Test
    public void constraintShouldCheckMasterCategory() {

    }
}