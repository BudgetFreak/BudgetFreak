package de.budgetfreak.accounting.web;

import de.budgetfreak.accounting.AccountTestUtils;
import de.budgetfreak.accounting.UserTestUtils;
import de.budgetfreak.accounting.domain.Account;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountResourceAssemblerTest {

    private AccountResourceAssembler testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new AccountResourceAssembler();
    }

    @Test
    public void shouldCreateResource() {
        Account entity = AccountTestUtils.createCheckingsAccount(UserTestUtils.createBob());
        AccountResource resource = testSubject.toResource(entity);

        assertThat(resource.getDescription()).isEqualTo(entity.getDescription());
        assertThat(resource.getOnBudget()).isEqualTo(entity.isOnBudget());
    }
}