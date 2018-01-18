package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.DatabaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("users.sql")
public class UserRepositoryTest extends DatabaseTest {

    @Autowired
    private UserRepository testSubject;

    @Test
    public void shouldBeInitialized() throws Exception {
        assertThat(testSubject).isNotNull();
    }

    @Test
    public void shouldNotBeEmpty() throws Exception {
        assertThat(testSubject.findAll()).hasSize(2);
    }

    @Test
    public void maxShouldBeHere() throws Exception {
        final User max = testSubject.findByName("Max");
        assertThat(max).isNotNull();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(max.getCurrency()).isEqualTo("€");
        softly.assertAll();
        assertThat(testSubject.findAll()).hasSize(2);
    }

    @Test
    public void mariaShouldBeHere() throws Exception {
        final User maria = testSubject.findByName("Maria");
        assertThat(maria).isNotNull();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(maria.getCurrency()).isEqualTo("$");
        softly.assertAll();
        assertThat(testSubject.findAll()).hasSize(2);
    }

    @Test
    public void shouldSaveNewEntites() throws Exception {
        final User manuel = new User("Manuel", "€");
        testSubject.saveAndFlush(manuel);
        assertThat(testSubject.findAll()).hasSize(3);
    }
}