package de.budgetfreak.budgeting.domain;

import de.budgetfreak.usermanagement.domain.User;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
@Import(TestDataService.class)
@TestPropertySource(locations = "/application-test.properties")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository testSubject;

    @Autowired
    private TestDataService testDataService;

    @Test
    public void shouldSaveNewMaterCategories() {
        User user = testDataService.createUser();
        MasterCategory masterCategory = testDataService.createMasterCategory(user);

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

    @Test
    public void shouldSaveUserDefinedCategoryWithMasterCategory() {
        User user = testDataService.createUser();
        MasterCategory masterCategory = testDataService.createMasterCategory(user);

        Category validCategory = new Category("test", user, CategoryType.USER_DEFINED).setMasterCategory(masterCategory);
        assertThatCode(() -> testSubject.saveAndFlush(validCategory)).doesNotThrowAnyException();
    }

    @Test
    public void shouldNotSaveUserDefinedCategoryWithoutMasterCategory() {
        User user = testDataService.createUser();

        Category inValidCategory = new Category("test", user, CategoryType.USER_DEFINED);
        assertThatCode(() -> testSubject.saveAndFlush(inValidCategory)).hasCauseInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void shouldSaveSystemCategoryWithoutMasterCategory() {
        User user = testDataService.createUser();

        Category validCategory = new Category("test", user, CategoryType.INCOME_THIS_MONTH);
        assertThatCode(() -> testSubject.saveAndFlush(validCategory)).doesNotThrowAnyException();
    }

    @Test
    public void shouldNotSaveUserDefinedCategoryWithMasterCategory() {
        User user = testDataService.createUser();
        MasterCategory masterCategory = testDataService.createMasterCategory(user);

        Category validCategory = new Category("test", user, CategoryType.INCOME_THIS_MONTH).setMasterCategory(masterCategory);
        assertThatCode(() -> testSubject.saveAndFlush(validCategory)).hasCauseInstanceOf(ConstraintViolationException.class);
    }
}