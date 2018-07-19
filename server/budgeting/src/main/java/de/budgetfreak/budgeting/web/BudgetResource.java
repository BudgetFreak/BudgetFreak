package de.budgetfreak.budgeting.web;

import de.budgetfreak.utils.web.ResourceEmbeddableSupport;

/**
 * Ressource representing a budget for a month.
 */
public class BudgetResource extends ResourceEmbeddableSupport {

    private final long year;
    private final long month;
    private final double carryover;
    private final double income;

    public BudgetResource(long year, long month, double carryover, double income) {
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

    public double getCarryover() {
        return carryover;
    }

    public double getIncome() {
        return income;
    }
}
