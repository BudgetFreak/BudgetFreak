package de.budgetfreak.accounting.web;

import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.web.ResourceLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Creates {@link Link}s for a given {@link User} using the {@link AccountController}.
 */
@Component
public class AccountControllerUserLinks extends ResourceLinks<User> {
    public AccountControllerUserLinks() {
        super(User.class);
    }

    @Override
    public Collection<Link> generateLinks(User entity) {
        Link accounts = linkTo(methodOn(AccountController.class).list(entity.getId())).withRel("accounts");
        return asList(accounts);
    }
}
