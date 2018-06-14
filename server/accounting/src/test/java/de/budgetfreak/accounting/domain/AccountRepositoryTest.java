package de.budgetfreak.accounting.domain;

import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Entity;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql(value = "accounts.sql")
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository testSubject;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldBeInitialized() {
        assertThat(testSubject).isNotNull();
    }

    @Test
    public void shouldNotBeEmpty() {
        assertThat(testSubject.count()).isEqualTo(3);
    }

    @Test
    public void maxShouldHaveTwoAccounts() {
        User max = userRepository.findByName("Max");
        List<Account> maxAccounts = testSubject.findByUserId(max.getId());
        assertThat(maxAccounts).hasSize(2);
        assertThat(maxAccounts).extracting("description").containsExactlyInAnyOrder("Giro", "Bargeld");
    }

    @Test
    public void mariaShouldHaveOneAccount() {
        User max = userRepository.findByName("Maria");
        List<Account> maxAccounts = testSubject.findByUserId(max.getId());
        assertThat(maxAccounts).hasSize(1);
        assertThat(maxAccounts.get(0).getDescription()).isEqualTo("Sparbuch");
    }
}