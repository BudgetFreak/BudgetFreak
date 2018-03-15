package de.budgetfreak.budgetfreakapplication.account;

import de.budgetfreak.budgetfreakapplication.user.UserResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link UserResource}.
 */
@Component
public class AccountResourceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountResource.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "onBudget", "onBudget.empty");
    }
}
