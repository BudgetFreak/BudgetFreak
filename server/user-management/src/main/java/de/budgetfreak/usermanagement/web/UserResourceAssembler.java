package de.budgetfreak.usermanagement.web;

import de.budgetfreak.usermanagement.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Assembler for converting an {@link User} into an {@link UserResource}.
 */
@Component
public class UserResourceAssembler extends ResourceAssembler<User, UserResource> {

    @Autowired
    public UserResourceAssembler(List<ResourceLinks<User>> resourceLinks) {
        super(UserController.class, UserResource.class, resourceLinks);
    }

    @Override
    protected UserResource createResource(User user) {
        return new UserResource(user.getName(), user.getCurrency());
    }
}
