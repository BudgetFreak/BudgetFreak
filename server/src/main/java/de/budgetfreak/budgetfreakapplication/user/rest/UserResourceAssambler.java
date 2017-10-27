package de.budgetfreak.budgetfreakapplication.user.rest;

import de.budgetfreak.budgetfreakapplication.user.domain.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class UserResourceAssambler extends ResourceAssemblerSupport<User, UserResource> {

    //TODO DOKU
    public UserResourceAssambler() {
        super(UserController.class, UserResource.class);
    }

    //TODO DOKU
    @Override
    public UserResource toResource(User entity) {
        UserResource userResource = new UserResource(entity.getName());
        userResource.add(linkTo(methodOn(UserController.class).getUser()).withRel("self"));
        return userResource;
    }
}
