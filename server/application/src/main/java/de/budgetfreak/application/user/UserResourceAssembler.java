package de.budgetfreak.application.user;

import de.budgetfreak.application.account.AccountController;
import de.budgetfreak.application.user.domain.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Assembler for {@link UserResource}.
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User entity) {
        final UserResource userResource = new UserResource(entity.getName(), entity.getCurrency());
        userResource.add(linkTo(methodOn(UserController.class).get(entity.getId())).withSelfRel());
        userResource.add(linkTo(methodOn(AccountController.class).list(entity.getId())).withRel("accounts"));
        return userResource;
    }
}
