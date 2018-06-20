package de.budgetfreak.usermanagement.web;

import de.budgetfreak.usermanagement.domain.User;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Creates {@link Link}s for a given {@link User} using the {@link UserController}.
 */
@Component
public class UserControllerUserLinks extends ResourceLinks<User> {
    public UserControllerUserLinks() {
        super(User.class);
    }

    @Override
    public Collection<Link> generateLinks(User entity) {
        Link self = linkTo(methodOn(UserController.class).get(entity.getId())).withSelfRel();
        Link users = linkTo(methodOn(UserController.class).list()).withRel("users");
        return Arrays.asList(self, users);
    }
}
