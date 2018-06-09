package de.budgetfreak.usermanagement.web;

import de.budgetfreak.usermanagement.domain.User;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceLinks extends ResourceLinks<User> {
    public UserResourceLinks() {
        super(User.class);
    }

    @Override
    public Iterable<Link> generateLinks(User entity) {
        Link self = linkTo(methodOn(UserController.class).get(entity.getId())).withSelfRel();
        Link users = linkTo(methodOn(UserController.class).list()).withRel("users");
        return Arrays.asList(self, users);
    }
}
