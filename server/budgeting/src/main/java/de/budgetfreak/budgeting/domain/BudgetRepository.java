package de.budgetfreak.budgeting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Budget}-entities.
 */
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
