package de.budgetfreak.application.account;

import static de.budgetfreak.application.user.UserTestUtils.createBob;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import de.budgetfreak.application.account.domain.Account;
import de.budgetfreak.application.account.domain.AccountRepository;
import de.budgetfreak.application.user.domain.User;
import de.budgetfreak.application.user.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AccountServiceTest {

    private AccountService testSubject;
    private AccountRepository accountRepositoryMock;
    private UserRepository userRepositoryMock;

    @Before
    public void setUp() throws Exception {
        accountRepositoryMock = mock(AccountRepository.class);
        userRepositoryMock = mock(UserRepository.class);
        testSubject = new AccountService(accountRepositoryMock, userRepositoryMock);
    }

    @Test
    public void shouldListAccounts() {
        User bob = createBob();
        Account checkings = AccountTestUtils.createCheckingsAccount(bob);
        Account savings = AccountTestUtils.createSavingsAccount(bob);
        when(accountRepositoryMock.findByUserId(bob.getId())).thenReturn(asList(checkings, savings));

        List<Account> accounts = testSubject.list(bob.getId());

        assertThat(accounts).containsExactlyInAnyOrder(checkings, savings);
    }

    @Test
    public void shoudlGetAccount() {
        User bob = createBob();
        Account checkings = AccountTestUtils.createCheckingsAccount(bob);
        when(accountRepositoryMock.findOne(bob.getId())).thenReturn(checkings);

        Account accounts = testSubject.get(checkings.getId());

        assertThat(accounts).isEqualTo(checkings);
    }

    @Test
    public void shouldCreateAnAccount() {
        User bob = createBob();
        when(userRepositoryMock.getOne(bob.getId())).thenReturn(bob);
        when(accountRepositoryMock.saveAndFlush(isA(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Account account = testSubject.create(bob.getId(), "Cash", true);

        assertThat(account).isNotNull();
        assertThat(account.getUser()).isNotNull();
        assertThat(account.getUser().getId()).isEqualTo(bob.getId());
        assertThat(account.getDescription()).isEqualTo("Cash");
        assertThat(account.isOnBudget()).isTrue();
    }
}