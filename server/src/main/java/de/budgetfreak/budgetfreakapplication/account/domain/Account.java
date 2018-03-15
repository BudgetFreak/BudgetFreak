package de.budgetfreak.budgetfreakapplication.account.domain;

import javax.persistence.*;

/**
 * Entity representing an account.
 */
@Entity(name = "BF_ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "on_budget")
    private boolean onBudget;

    public Account() {
    }

    public Account(String description, boolean onBudget) {
        this.description = description;
        this.onBudget = onBudget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOnBudget() {
        return onBudget;
    }

    public void setOnBudget(boolean onBudget) {
        this.onBudget = onBudget;
    }
}
