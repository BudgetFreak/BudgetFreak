package de.budgetfreak.budgetfreakapplication.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.budgetfreak.budgetfreakapplication.user.domain.User;
import de.budgetfreak.budgetfreakapplication.user.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    private UserService testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new UserService(userRepositoryMock);
    }

    @Test
    public void shouldCreateUser() throws Exception {
        when(userRepositoryMock.save(isA(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        final User user = testSubject.create("Clark", "$");

        assertThat(user.getName()).isEqualTo("Clark");
        assertThat(user.getCurrency()).isEqualTo("$");
    }

    @Test
    public void shouldReturnAllUser() throws Exception {
        final List<User> bobAndJane = UserTestUtils.createBobAndJane();
        when(userRepositoryMock.findAll()).thenReturn(bobAndJane);

        final List<User> list = testSubject.list();

        assertThat(list).isEqualTo(bobAndJane);
    }
}