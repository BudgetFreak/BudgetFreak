package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Controller for accessing users.
 */
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

    /**
     * List all users in no given order.
     */
    @GetMapping
    public Resources<UserResource> list() {
        List<User> users = userService.list();
        List<UserResource> resources = userResourceAssembler.toResources(users);
        final Link selfRel = linkTo(methodOn(UserController.class).list()).withSelfRel();
        return new Resources<>(resources, selfRel);
    }

    /**
     * Create an user.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> create(@RequestBody UserResource userResource) {
        // TODO: Validate UserResource before processing
        User user = userService.create(userResource.getName(), userResource.getCurrency());
        final UserResource createdUserResource = userResourceAssembler.toResource(user);
        return new ResponseEntity<>(createdUserResource, HttpStatus.OK);
    }
}
