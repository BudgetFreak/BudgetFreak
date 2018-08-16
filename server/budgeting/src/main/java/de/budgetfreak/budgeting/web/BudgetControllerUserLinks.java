package de.budgetfreak.budgeting.web;

import de.budgetfreak.accounting.web.AccountController;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.utils.web.ResourceLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        @SuppressWarnings("squid:S2637") // Passing null values to @NonNull parameters here. Spring will create placeholders for them in the link.
                Link budget = linkTo(methodOn(BudgetController.class).get(entity.getId(), null, null)).withRel("budget");
        return singletonList(budget);
    }
}
