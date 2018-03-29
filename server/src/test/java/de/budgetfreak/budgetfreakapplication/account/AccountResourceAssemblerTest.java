package de.budgetfreak.budgetfreakapplication.account;

import static de.budgetfreak.budgetfreakapplication.account.AccountTestUtils.createCheckingsAccount;
import static de.budgetfreak.budgetfreakapplication.user.UserTestUtils.createBob;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.budgetfreak.budgetfreakapplication.account.domain.Account;
import de.budgetfreak.budgetfreakapplication.user.UserTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

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