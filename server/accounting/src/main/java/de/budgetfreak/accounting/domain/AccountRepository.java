package de.budgetfreak.accounting.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for {@link Account}-entities.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserId(long userId);
}
