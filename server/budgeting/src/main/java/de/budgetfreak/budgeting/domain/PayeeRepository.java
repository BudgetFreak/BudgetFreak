package de.budgetfreak.budgeting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Payee}-entities.
 */
public interface PayeeRepository extends JpaRepository<Payee, Long> {
}
