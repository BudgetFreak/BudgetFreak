package de.budgetfreak.budgeting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link MasterCategory}-entities.
 */
public interface MasterCategoryRepository extends JpaRepository<MasterCategory, Long> {
}
