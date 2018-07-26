package de.budgetfreak.accounting.web;

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
@WebMvcTest(value = AccountControllerUserLinks.class)
public class AccountControllerUserLinksTest {

    @InjectMocks
    private AccountControllerUserLinks testSubject;

    @Test
    public void generateLinks() throws Exception {
        List<Link> links = new ArrayList<>(testSubject.generateLinks(createUser()));

        assertThat(links.size()).isEqualTo(1);
        assertThat(links.get(0).getRel()).isEqualTo("accounts");
        assertThat(links.get(0).getHref()).isEqualTo("/users/1/accounts");
    }

    private User createUser() {
        User user = new User();
        user.setName("Parry Hotter");
        user.setCurrency("?");
        user.setId(1L);
        return user;
    }

}
