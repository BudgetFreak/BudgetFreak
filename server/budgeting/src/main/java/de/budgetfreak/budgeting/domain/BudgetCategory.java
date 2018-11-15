package de.budgetfreak.budgeting.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * Represents the budget for a category for a specific moment in time.
 */
public class BudgetCategory {

    private long id;
    private String name;
    private final BigDecimal budget;
    private final BigDecimal expenses;
    private MasterCategory masterCategory;

    public BudgetCategory(long id, String name, BigDecimal budget, BigDecimal expenses, MasterCategory masterCategory) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.expenses = expenses;
        this.masterCategory = masterCategory;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public MasterCategory getMasterCategory() {
        return masterCategory;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
