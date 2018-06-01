package de.budgetfreak.budgetfreakapplication.user;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class UserResourceValidatorTest {

    private UserResourceValidator testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new UserResourceValidator();
    }

    @Test
    public void nameShouldNotBeEmpty() {
        final UserResource userResource = new UserResource("", "€");
        final BeanPropertyBindingResult errors = createBindingResult(userResource);

        testSubject.validate(userResource, errors);

        assertThat(errors.getFieldError("name").getCode()).isEqualTo("name.empty");
    }

    @Test
    public void currencyShouldNotBeEmpty() {
        final UserResource userResource = new UserResource("Foo", "");
        final BeanPropertyBindingResult errors = createBindingResult(userResource);

        testSubject.validate(userResource, errors);

        assertThat(errors.getFieldError("currency").getCode()).isEqualTo("currency.empty");
    }

    private BeanPropertyBindingResult createBindingResult(UserResource userResource) {
        return new BeanPropertyBindingResult(userResource, "");
    }
}