package de.budgetfreak.budgetfreakapplication.account;

import de.budgetfreak.budgetfreakapplication.account.domain.Account;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountService {

    /**
     * Lists all accounts.
     */
    public List<Account> list() {
        return Collections.emptyList();
    }

    /**
     * Returns one account by id.
     */
    public Account get(long id) {
        return null;
    }

    /**
     * Creates an account.
     */
    public Account create(String description, boolean onBudget) {
        return new Account(description, onBudget);
    }
}
