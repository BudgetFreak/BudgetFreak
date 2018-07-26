package de.budgetfreak.usermanagement.web;

import de.budgetfreak.usermanagement.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ResourceLinksTest {

    @Test
    public void acceptsShouldAcceptCorrectClass() throws Exception {
        ResourceLinks testSubject = new UserControllerUserLinks();
        assertThat(testSubject.accepts(User.class)).isTrue();
    }

    @Test
    public void acceptsShouldNotAcceptIncorrectClass() throws Exception {
        ResourceLinks testSubject = new UserControllerUserLinks();
        assertThat(testSubject.accepts(String.class)).isFalse();
    }

}