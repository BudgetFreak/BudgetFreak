package de.budgetfreak.budgeting.web;

import de.budgetfreak.utils.web.ResourceEmbeddableSupport;

import java.math.BigDecimal;

/**
 * Ressource representing a budget for a month.
 */
public class BudgetResource extends ResourceEmbeddableSupport {

    private final long year;
    private final long month;
    private final BigDecimal carryover;
    private final BigDecimal income;

    public BudgetResource(long year, long month, BigDecimal carryover, BigDecimal income) {
        this.year = year;
        this.month = month;
        this.carryover = carryover;
        this.income = income;
    }

    public long getYear() {
        return year;
    }

    public long getMonth() {
        return month;
    }

    public BigDecimal getCarryover() {
        return carryover;
    }

    public BigDecimal getIncome() {
        return income;
    }
}
