package de.budgetfreak.budgetfreakapplication.account;

import de.budgetfreak.budgetfreakapplication.account.domain.Account;
import de.budgetfreak.budgetfreakapplication.account.domain.AccountRepository;
import de.budgetfreak.budgetfreakapplication.user.domain.User;
import de.budgetfreak.budgetfreakapplication.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param userId
     */
    public List<Account> list(long userId) {
        return accountRepository.findByUserId(userId);
    }

    /**
     * Returns one account by id.
     */
    public Account get(long id) {
        return accountRepository.findOne(id);
    }

    /**
     * Creates an account.
     */
    public Account create(long userId, String description, boolean onBudget) {
        User user = userRepository.getOne(userId);
        Account account = new Account(description, onBudget, user);
        return accountRepository.saveAndFlush(account);
    }
}
