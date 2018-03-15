package de.budgetfreak.budgetfreakapplication.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link UserResource}.
 */
@Component
public class UserResourceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserResource.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currency", "currency.empty");
    }
}
