package de.budgetfreak.usermanagement.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Sql(value = "users.sql")
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
public class UserRepositoryTest {

    @Autowired
    private UserRepository testSubject;

    @Test
    public void shouldBeInitialized() {
        assertThat(testSubject).isNotNull();
    }

    @Test
    public void shouldNotBeEmpty() {
        assertThat(testSubject.findAll()).hasSize(2);
    }

    @Test
    public void maxShouldBeHere() {
        final User max = testSubject.findByName("Max");
        assertThat(max).isNotNull();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(max.getCurrency()).isEqualTo("€");
        softly.assertAll();
        assertThat(testSubject.findAll()).hasSize(2);
    }

    @Test
    public void mariaShouldBeHere() {
        final User maria = testSubject.findByName("Maria");
        assertThat(maria).isNotNull();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(maria.getCurrency()).isEqualTo("$");
        softly.assertAll();
        assertThat(testSubject.findAll()).hasSize(2);
    }

    @Test
    public void shouldSaveNewEntites() {
        final User manuel = new User("Manuel", "€");
        testSubject.saveAndFlush(manuel);
        assertThat(testSubject.findAll()).hasSize(3);
    }
}