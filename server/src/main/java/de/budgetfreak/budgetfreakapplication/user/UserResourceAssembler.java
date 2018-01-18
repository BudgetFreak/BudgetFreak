package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.user.domain.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User entity) {
        final UserResource userResource = new UserResource(entity.getName(), entity.getCurrency());
        userResource.add(linkTo(methodOn(UserController.class).list()).withSelfRel());
        return userResource;
    }
}
