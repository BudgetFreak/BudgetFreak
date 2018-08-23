package de.budgetfreak.demousergeneration.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.budgetfreak.accounting.service.AccountService;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.service.UserService;
import de.budgetfreak.usermanagement.web.UserResource;
import de.budgetfreak.usermanagement.web.UserResourceAssembler;

/**
 * Controller for creating a demo user with example data
 */
@RestController
@RequestMapping("/demouser")
public class DemoUserController {

    private final UserService userService;
    private final AccountService accountService;
    private final UserResourceAssembler userResourceAssembler;

    @Autowired
    public DemoUserController(UserService userService, AccountService accountService, UserResourceAssembler userResourceAssembler) {
        this.userService = userService;
        this.accountService = accountService;
        this.userResourceAssembler = userResourceAssembler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> create(@RequestBody @Valid DemoUserNameResource userName) {

        User user = userService.create(userName.getUserName(), "?");
        createDemoAccountsForUser(user);
        UserResource userResource = userResourceAssembler.toResource(user);
        return new ResponseEntity<>(userResource, HttpStatus.OK);
    }

    private void createDemoAccountsForUser(User user) {
        accountService.create(user.getId(), "Household", true);
        accountService.create(user.getId(), "Renovations", true);
        accountService.create(user.getId(), "Budget for buying fun stuff", false);
    }
}
