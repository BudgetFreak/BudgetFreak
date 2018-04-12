package de.budgetfreak.budgetfreakapplication.account;

import org.springframework.hateoas.ResourceSupport;

/**
 * Ressource representing an account.
 */
public class AccountResource extends ResourceSupport {

    private String description;
    private Boolean onBudget;

    public AccountResource() {
    }

    public AccountResource(String description, Boolean onBudget) {
        this.description = description;
        this.onBudget = onBudget;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getOnBudget() {
        return onBudget;
    }
}
