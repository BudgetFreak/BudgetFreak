package de.budgetfreak.budgetfreakapplication.account;

import de.budgetfreak.budgetfreakapplication.account.domain.Account;
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
@RequestMapping("/users/{userId}/accounts")
public class AccountController {

    private AccountService accountService;
    private AccountResourceAssembler accountResourceAssembler;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new AccountResourceValidator());
    }


    @Autowired
    public AccountController(AccountService accountService, AccountResourceAssembler accountResourceAssembler) {
        this.accountService = accountService;
        this.accountResourceAssembler = accountResourceAssembler;
    }

    /**
     * List all accounts in no given order.
     */
    @GetMapping
    public Resources<AccountResource> list(@PathVariable("userId") long userId) {
        List<Account> accounts = accountService.list(userId);
        List<AccountResource> resources = accountResourceAssembler.toResources(accounts);
        final Link selfRel = linkTo(methodOn(AccountController.class).list(userId)).withSelfRel();
        return new Resources<>(resources, selfRel);
    }

    /**
     * Get one account.
     */
    @GetMapping("/{id}")
    public Resource<AccountResource> get(@PathVariable("userId") long userId, @PathVariable("id") long id) {
        Account account = accountService.get(id);
        final AccountResource accountResource = accountResourceAssembler.toResource(account);
        return new Resource<>(accountResource);
    }

    /**
     * Create an account.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResource> create(@PathVariable("userId") long userId, @RequestBody @Valid AccountResource accountResource) {
        Account account = accountService.create(userId, accountResource.getDescription(), accountResource.isOnBudget());
        final AccountResource createdAccountResource = accountResourceAssembler.toResource(account);
        return new ResponseEntity<>(createdAccountResource, HttpStatus.OK);
    }
}
