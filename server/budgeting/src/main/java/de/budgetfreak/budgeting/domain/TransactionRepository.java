package de.budgetfreak.budgeting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Transaction}-entities.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
