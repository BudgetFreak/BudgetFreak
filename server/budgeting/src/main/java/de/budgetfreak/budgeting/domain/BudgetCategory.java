package de.budgetfreak.budgeting.domain;

import java.math.BigDecimal;

/**
 * Represents the budget for a category for a specific moment in time.
 */
public class BudgetCategory {

    private String name;
    private BigDecimal balance;

    public BudgetCategory(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
