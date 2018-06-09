package de.budgetfreak.usermanagement;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.budgetfreak.usermanagement.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Ignore
    public void shouldReadTestProperty() {
        assertThat(testSubject.getTestProperty()).isEqualTo("foobar");
    }

    @SpringBootApplication
    static class TestConfiguration {

    }
}