package de.budgetfreak.accounting.service;

import de.budgetfreak.accounting.AccountTestUtils;
import de.budgetfreak.accounting.UserTestUtils;
import de.budgetfreak.accounting.domain.Account;
import de.budgetfreak.accounting.domain.AccountRepository;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private AccountService testSubject;
    private AccountRepository accountRepositoryMock;
    private UserRepository userRepositoryMock;

    @Before
    public void setUp() {
        accountRepositoryMock = mock(AccountRepository.class);
        userRepositoryMock = mock(UserRepository.class);
        testSubject = new AccountService(accountRepositoryMock, userRepositoryMock);
    }

    @Test
    public void shouldListAccounts() {
        User bob = UserTestUtils.createBob();
        Account checkings = AccountTestUtils.createCheckingsAccount(bob);
        Account savings = AccountTestUtils.createSavingsAccount(bob);
        when(accountRepositoryMock.findByUserId(bob.getId())).thenReturn(asList(checkings, savings));

        List<Account> accounts = testSubject.list(bob.getId());

        assertThat(accounts).containsExactlyInAnyOrder(checkings, savings);
    }

    @Test
    public void shoudlGetAccount() {
        User bob = UserTestUtils.createBob();
        Account checkings = AccountTestUtils.createCheckingsAccount(bob);
        when(accountRepositoryMock.findOne(isA(Example.class))).thenReturn(Optional.of(checkings));

        Account accounts = testSubject.get(checkings.getId());

        assertThat(accounts).isEqualTo(checkings);
    }

    @Test
    public void shouldCreateAnAccount() {
        User bob = UserTestUtils.createBob();
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