package de.budgetfreak.budgetfreakapplication.account;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;

public class AccountResourceValidatorTest {
    
    private AccountResourceValidator testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new AccountResourceValidator();
    }

    @Test
    public void descriptionShouldNotBeEmpty() {
        final AccountResource resource = new AccountResource("", true);
        final BeanPropertyBindingResult errors = createBindingResult(resource);

        testSubject.validate(resource, errors);

        assertThat(errors.getFieldError("description").getCode()).isEqualTo("description.empty");
    }

    @Test
    public void onBudgetShouldNotBeEmpty() {
        final AccountResource resource = new AccountResource("Cash", null);
        final BeanPropertyBindingResult errors = createBindingResult(resource);

        testSubject.validate(resource, errors);

        assertThat(errors.getFieldError("onBudget").getCode()).isEqualTo("onBudget.empty");
    }

    private BeanPropertyBindingResult createBindingResult(AccountResource AccountResource) {
        return new BeanPropertyBindingResult(AccountResource, "");
    }
}