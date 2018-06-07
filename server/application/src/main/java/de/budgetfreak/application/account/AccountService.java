package de.budgetfreak.application.account;

import de.budgetfreak.application.account.domain.Account;
import de.budgetfreak.application.account.domain.AccountRepository;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing accounts.
 */
@Service
public class AccountService {

    private AccountRepository accountRepository;
    private UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    /**
     * Lists all accounts.
     *
     * @param userId The id of the user who's account we want to access.
     */
    public List<Account> list(long userId) {
        return accountRepository.findByUserId(userId);
    }

    /**
     * Returns one account by id.
     *
     * @param id The id of the account.
     */
    public Account get(long id) {
        return accountRepository.findOne(Example.of(new Account().setId(id))).orElse(null);
    }

    /**
     * Creates an account.
     *
     * @param userId      The user the account will belong to.
     * @param description The description.
     * @param onBudget    Whether the account will have an effect on the budget.
     */
    public Account create(long userId, String description, boolean onBudget) {
        User user = userRepository.getOne(userId);
        Account account = new Account(description, onBudget, user);
        return accountRepository.saveAndFlush(account);
    }
}
