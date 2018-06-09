package de.budgetfreak.usermanagement.web;

import de.budgetfreak.usermanagement.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Assembler for {@link UserResource}.
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    private List<ResourceLinks> resourceLinks;

    @Autowired
    public UserResourceAssembler(List<ResourceLinks> resourceLinks) {
        super(UserController.class, UserResource.class);
        this.resourceLinks = resourceLinks;
    }

    @Override
    public UserResource toResource(User entity) {
        final UserResource userResource = new UserResource(entity.getName(), entity.getCurrency());

        resourceLinks.stream().filter(link -> link.accepts(User.class)).forEach(link -> {
            userResource.add(link.generateLinks(entity));
        });

        return userResource;
    }
}
