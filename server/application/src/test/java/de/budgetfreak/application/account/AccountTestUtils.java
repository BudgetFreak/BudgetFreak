package de.budgetfreak.application.account;

import de.budgetfreak.application.account.domain.Account;
import de.budgetfreak.application.user.domain.User;

import java.util.List;

import static java.util.Arrays.asList;

public class AccountTestUtils {

    private AccountTestUtils() {
    }

    public static List<Account> createAccounts(User user) {
        return asList(createCheckingsAccount(user), createSavingsAccount(user));
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
