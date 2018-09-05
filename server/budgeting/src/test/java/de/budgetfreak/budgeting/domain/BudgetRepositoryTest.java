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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
public class BudgetRepositoryTest {

    @Autowired
    private BudgetRepository testSubject;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveNewMaterCategories() {
        User user = userRepository.save(new User().setName("Bob").setCurrency("€"));

        Budget budget = new Budget().setYear(2018).setMonth(8).setUser(user);
        Long budgetId = testSubject.save(budget).getId();

        Optional<Budget> savedBudgetOptional = testSubject.findById(budgetId);
        assertThat(savedBudgetOptional).isPresent();
        Budget savedBudget = savedBudgetOptional.get();
        assertThat(savedBudget.getYear()).isEqualTo(2018);
        assertThat(savedBudget.getMonth()).isEqualTo(8);
        assertThat(savedBudget.getUser().getId()).isEqualTo(user.getId());

    }
}