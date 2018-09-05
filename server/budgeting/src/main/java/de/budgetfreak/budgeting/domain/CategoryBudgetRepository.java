package de.budgetfreak.budgeting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link CategoryBudget}-entities.
 */
public interface CategoryBudgetRepository extends JpaRepository<CategoryBudget, Long> {
}
