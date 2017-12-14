package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.IntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("users.sql")
public class UserRepositoryTest extends IntegrationTest {

    @Autowired
    private UserRepository testSubject;

    @Test
    public void shouldBeInitialized() throws Exception {
        assertThat(testSubject).isNotNull();
    }

    @Test
    public void maxShouldBehHere() throws Exception {
        final User max = testSubject.findOne(1L);
        assertThat(max).isNotNull();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(max.getName()).isEqualTo("Max");
        softly.assertThat(max.getCurrency()).isEqualTo("€");
        softly.assertAll();
    }

    @Test
    public void mariaShouldBehHere() throws Exception {
        final User maria = testSubject.findOne(2L);
        assertThat(maria).isNotNull();
        final SoftAssertions softly = new SoftAssertions();
        softly.assertThat(maria.getName()).isEqualTo("Maria");
        softly.assertThat(maria.getCurrency()).isEqualTo("$");
        softly.assertAll();
    }
}