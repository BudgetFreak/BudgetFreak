package de.budgetfreak.budgetfreakapplication.user.rest;

import de.budgetfreak.budgetfreakapplication.user.domain.User;
import de.budgetfreak.budgetfreakapplication.user.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    private UserResourceAssambler userResourceAssambler;

    @Autowired
    public UserController(UserService userService, UserResourceAssambler userResourceAssambler) {
        this.userService = userService;
        this.userResourceAssambler = userResourceAssambler;
    }

    @RequestMapping(value = "/users", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<UserResource> getUser() {
        List<User> allUser = userService.getAllUser();
        List<UserResource> userResources = userResourceAssambler.toResources(allUser);
        Link self = linkTo(methodOn(UserController.class).getUser()).withRel("self");
        return new Resources<>(userResources, self);
    }

    @RequestMapping(method = POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> createUser(@RequestParam(name = "name", required = true) String name) {
        User user = userService.createUser(name);
        UserResource userResource = userResourceAssambler.toResource(user);
        return new ResponseEntity<UserResource>(userResource, HttpStatus.OK);
    }
}
