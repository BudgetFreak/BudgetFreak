package de.budgetfreak.usermanagement;

import de.budgetfreak.usermanagement.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // TODO Maybe define global validators?
        binder.setValidator(new UserResourceValidator());
    }


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
     * Get one user.
     */
    @GetMapping("/{userId}")
    public Resource<UserResource> get(@PathVariable("userId") long userId) {
        User user = userService.get(userId);
        final UserResource userResource = userResourceAssembler.toResource(user);
        return new Resource<>(userResource);
    }

    /**
     * Create an user.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> create(@RequestBody @Valid UserResource userResource) {
        User user = userService.create(userResource.getName(), userResource.getCurrency());
        final UserResource createdUserResource = userResourceAssembler.toResource(user);
        return new ResponseEntity<>(createdUserResource, HttpStatus.OK);
    }
}
