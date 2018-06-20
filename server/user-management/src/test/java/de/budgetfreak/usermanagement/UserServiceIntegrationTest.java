package de.budgetfreak.usermanagement;

import de.budgetfreak.usermanagement.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService testSubject;

    @Test
    public void contextLoads() {
        assertThat(testSubject).isNotNull();
    }

    @Test
    public void shouldReadTestProperty() {
        assertThat(testSubject.getTestProperty()).isEqualTo("property from application.properties in user-management");
    }
}