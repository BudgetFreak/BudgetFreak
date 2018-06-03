package de.budgetfreak.application.account;

import static de.budgetfreak.application.account.AccountTestUtils.createCheckingsAccount;
import static de.budgetfreak.application.user.UserTestUtils.createBob;
import static org.assertj.core.api.Assertions.*;

import de.budgetfreak.application.account.domain.Account;
import org.junit.Before;
import org.junit.Test;

public class AccountResourceAssemblerTest {

    private AccountResourceAssembler testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new AccountResourceAssembler();
    }

    @Test
    public void shouldCreateResource() {
        Account entity = createCheckingsAccount(createBob());
        AccountResource resource = testSubject.toResource(entity);

        assertThat(resource.getDescription()).isEqualTo(entity.getDescription());
        assertThat(resource.getOnBudget()).isEqualTo(entity.isOnBudget());
    }
}