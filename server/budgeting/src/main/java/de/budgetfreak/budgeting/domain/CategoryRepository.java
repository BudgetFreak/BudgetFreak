package de.budgetfreak.budgeting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Category}-entities.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
