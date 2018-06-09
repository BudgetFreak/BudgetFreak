package de.budgetfreak.application.user;

import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService testSubject;
    private UserRepository userRepositoryMock;

    @Before
    public void setUp() throws Exception {
        userRepositoryMock = mock(UserRepository.class);
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

    @Test
    public void shouldFindOneUser() {
        final User bob = UserTestUtils.createBob();
        when(userRepositoryMock.findOne(isA(Example.class))).thenReturn(Optional.of(bob));

        final User user = testSubject.get(bob.getId());
        assertThat(user).isEqualToComparingFieldByField(bob);
    }
}