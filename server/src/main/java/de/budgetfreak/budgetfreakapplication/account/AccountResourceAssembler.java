package de.budgetfreak.budgetfreakapplication.account;

import de.budgetfreak.budgetfreakapplication.account.domain.Account;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class AccountResourceAssembler extends ResourceAssemblerSupport<Account, AccountResource> {

    public AccountResourceAssembler() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account entity) {
        final AccountResource accountResource = new AccountResource(entity.getDescription(), entity.isOnBudget());
        accountResource.add(linkTo(methodOn(AccountController.class).get(entity.getId())).withSelfRel());
        return accountResource;
    }
}
