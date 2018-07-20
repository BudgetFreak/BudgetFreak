package de.budgetfreak.usermanagement.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.Link;
import org.springframework.test.context.junit4.SpringRunner;

import de.budgetfreak.usermanagement.domain.User;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserControllerUserLinks.class)
public class UserControllerUserLinksTest {

    @InjectMocks
    private UserControllerUserLinks testSubject;

    @Test
    public void generateLinks() {
        List<Link> links = new ArrayList<>(testSubject.generateLinks(createUser()));

        assertThat(links.size()).isEqualTo(2);
        assertThat(links.get(0).getRel()).isEqualTo("self");
        assertThat(links.get(0).getHref()).isEqualTo("/users/1");
        assertThat(links.get(1).getRel()).isEqualTo("users");
        assertThat(links.get(1).getHref()).isEqualTo("/users");
    }

    private User createUser() {
        User user = new User("Jack McClane", "$");
        user.setId(1L);
        return user;
    }
}
