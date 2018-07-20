package de.budgetfreak.usermanagement.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import de.budgetfreak.usermanagement.domain.User;

@RunWith(SpringRunner.class)
public class UserResourceAssemblerTest {

    private static final String NAME = "Geralt of Rivia";
    private static final String CURRENCY = "?";

    @InjectMocks
    private UserResourceAssembler testSubject;

    @Test
    public void createResource() throws Exception {
        UserResource userResource = testSubject.createResource(new User(NAME, CURRENCY));
        assertThat(userResource.getName()).isEqualTo(NAME);
        assertThat(userResource.getCurrency()).isEqualTo(CURRENCY);
    }
}
