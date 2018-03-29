package de.budgetfreak.budgetfreakapplication.account;

import de.budgetfreak.budgetfreakapplication.account.domain.Account;
import de.budgetfreak.budgetfreakapplication.user.domain.User;

public class AccountTestUtils {

    private AccountTestUtils() {
    }

    public static Account createCheckingsAccount(User user) {
        return createAccount(1L, "Checkings", true, user);
    }

    public static Account createSavingsAccount(User user) {
        return createAccount(2L, "Savings", false, user);
    }

    public static Account createAccount(long id, String description, boolean onBudget, User user) {
        Account account = new Account(description, onBudget, user).setId(id);
        return account;
    }
}
