package de.budgetfreak.budgeting.specification;

import de.budgetfreak.budgeting.domain.Category;
import de.budgetfreak.budgeting.domain.CategoryRepository;
import de.budgetfreak.budgeting.domain.MasterCategory;
import de.budgetfreak.budgeting.domain.MasterCategoryRepository;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
@Import(CategorySpecifications.class)
public class CategorySpecificationsTest {

    @Autowired
    private CategorySpecifications testSubject;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MasterCategoryRepository masterCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void byUser() {
        User user = userRepository.save(new User("Bob", "€"));
        MasterCategory mcMonthly = masterCategoryRepository.save(new MasterCategory().setName("Monthly").setUser(user));
        MasterCategory mcSavings = masterCategoryRepository.save(new MasterCategory().setName("Savings").setUser(user));

        categoryRepository.saveAll(asList(
                new Category().setName("Rent").setMasterCategory(mcMonthly),
                new Category().setName("Internet").setMasterCategory(mcMonthly),
                new Category().setName("Rainy Day Funds").setMasterCategory(mcSavings),
                new Category().setName("Christmas").setMasterCategory(mcSavings)
        ));


        Specification<Category> specification = testSubject.byUser(user);
        List<Category> categories = categoryRepository.findAll(specification);

        assertThat(categories).extracting("name").containsExactlyInAnyOrder("Rent", "Internet", "Rainy Day Funds", "Christmas");
    }
}