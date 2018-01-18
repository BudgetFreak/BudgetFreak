package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserController(UserService userService, UserResourceAssembler userResourceAssembler) {
        this.userService = userService;
        this.userResourceAssembler = userResourceAssembler;
    }

    @GetMapping
    public Resources<UserResource> list() {
        List<User> users = userService.list();
        List<UserResource> resources = userResourceAssembler.toResources(users);
        final Link selfRel = linkTo(methodOn(UserController.class).list()).withSelfRel();
        return new Resources<>(resources, selfRel);
    }
}
