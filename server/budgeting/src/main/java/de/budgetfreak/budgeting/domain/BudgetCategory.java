package de.budgetfreak.budgeting.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * Represents the budget for a category for a specific moment in time.
 */
public class BudgetCategory {

    private String name;
    private BigDecimal balance;
    private MasterCategory masterCategory;

    public BudgetCategory(String name, BigDecimal balance, MasterCategory masterCategory) {
        this.name = name;
        this.balance = balance;
        this.masterCategory = masterCategory;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public MasterCategory getMasterCategory() {
        return masterCategory;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
