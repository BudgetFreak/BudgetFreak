package de.budgetfreak.usermanagement.service;

import de.budgetfreak.usermanagement.UserManagementProperties;
import de.budgetfreak.usermanagement.UserTestUtils;
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
    private UserManagementProperties userManagementProperties;

    @Before
    public void setUp() {
        userRepositoryMock = mock(UserRepository.class);
        userManagementProperties = new UserManagementProperties().setTestProperty("mockedTestProperty");
        testSubject = new UserService(userRepositoryMock, userManagementProperties);
    }

    @Test
    public void shouldCreateUser() {
        when(userRepositoryMock.save(isA(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        final User user = testSubject.create("Clark", "$");

        assertThat(user.getName()).isEqualTo("Clark");
        assertThat(user.getCurrency()).isEqualTo("$");
    }

    @Test
    public void shouldReturnAllUser() {
        final List<User> bobAndJane = UserTestUtils.createBobAndJane();
        when(userRepositoryMock.findAll()).thenReturn(bobAndJane);

        final List<User> list = testSubject.list();

        assertThat(list).isEqualTo(bobAndJane);
    }

    @Test
    public void shouldFindOneUser() {
        final User bob = UserTestUtils.createBob();
        when(userRepositoryMock.findOne(isA(Example.class))).thenReturn(Optional.of(bob));

        final Optional<User> user = testSubject.get(bob.getId());
        assertThat(user).get().isEqualToComparingFieldByField(bob);
    }

    @Test
    public void shouldReadTestProperty() {
        assertThat(testSubject.getTestProperty()).isEqualTo("mockedTestProperty");
    }
}