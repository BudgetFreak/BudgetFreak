package de.budgetfreak.budgeting.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "BF_CATEGORYBUDGET")
public class CategoryBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUDGET_ID")
    private Budget budget;

    public Long getId() {
        return id;
    }

    public CategoryBudget setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CategoryBudget setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryBudget setDescription(String description) {
        this.description = description;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public CategoryBudget setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Budget getBudget() {
        return budget;
    }

    public CategoryBudget setBudget(Budget budget) {
        this.budget = budget;
        return this;
    }
}
