package de.budgetfreak.accounting.domain;

import de.budgetfreak.usermanagement.domain.User;

import javax.persistence.*;

/**
 * Entity representing an account.
 */
@Entity(name = "BF_ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ON_BUDGET")
    private boolean onBudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Account() {
    }

    public Account(String description, boolean onBudget, User user) {
        this.description = description;
        this.onBudget = onBudget;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Account setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isOnBudget() {
        return onBudget;
    }

    public Account setOnBudget(boolean onBudget) {
        this.onBudget = onBudget;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Account setUser(User user) {
        this.user = user;
        return this;
    }
}
