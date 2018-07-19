package de.budgetfreak.budgeting.web;

import org.springframework.hateoas.ResourceSupport;

/**
 * Ressource representing a category in a budget.
 */
public class CategoryResource extends ResourceSupport {

    private String name;
    private double budget;
    private double expenses;
    private double previousBudget;
    private double previousExpenses;
    private double averageExpenses;

    public CategoryResource(String name, double budget, double expenses, double previousBudget, double previousExpenses, double averageExpenses) {
        this.name = name;
        this.budget = budget;
        this.expenses = expenses;
        this.previousBudget = previousBudget;
        this.previousExpenses = previousExpenses;
        this.averageExpenses = averageExpenses;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getPreviousBudget() {
        return previousBudget;
    }

    public double getPreviousExpenses() {
        return previousExpenses;
    }

    public double getAverageExpenses() {
        return averageExpenses;
    }
}
