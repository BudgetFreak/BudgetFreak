package de.budgetfreak.budgeting.domain;

import de.budgetfreak.usermanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for reading summarized budget data.
 */
public interface BudgetSummaryRepository extends JpaRepository<Budget, Long> {

    @Query("select new de.budgetfreak.budgeting.domain.BudgetCategory(" +
                "c.name, " +
                "(select sum(t.amount) from Transaction t where t.category = c), " +
                "(select mc from MasterCategory mc where mc.id = c.masterCategory.id) " +
            ") " +
            "from Category c " +
            "where c.masterCategory.user = :user")
    List<BudgetCategory> findAllBudgetCategories(@Param("user") User user);

}
