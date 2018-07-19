package de.budgetfreak.budgeting.web;

import de.budgetfreak.accounting.web.AccountController;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.web.ResourceLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Creates {@link Link}s for a given {@link User} using the {@link AccountController}.
 */
@Component
public class BudgetControllerUserLinks extends ResourceLinks<User> {
    public BudgetControllerUserLinks() {
        super(User.class);
    }

    @Override
    public Collection<Link> generateLinks(User entity) {
        Link budget = linkTo(methodOn(BudgetController.class).get(entity.getId(), null, null)).withRel("budget");
        return singletonList(budget);
    }
}
