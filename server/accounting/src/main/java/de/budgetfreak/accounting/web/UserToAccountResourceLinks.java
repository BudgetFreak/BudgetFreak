package de.budgetfreak.accounting.web;

import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.web.ResourceLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserToAccountResourceLinks extends ResourceLinks<User> {
    public UserToAccountResourceLinks() {
        super(User.class);
    }

    @Override
    public Iterable<Link> generateLinks(User entity) {
        Link accounts = linkTo(methodOn(AccountController.class).list(entity.getId())).withRel("accounts");
        return asList(accounts);
    }
}
