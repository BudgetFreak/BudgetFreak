package de.budgetfreak.budgeting.domain;

import de.budgetfreak.usermanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository for reading summarized budget data.
 */
public interface BudgetSummaryRepository extends JpaRepository<Budget, Long> {

    @Query("select new de.budgetfreak.budgeting.domain.BudgetCategory(" +
            "c.id, " +
                "c.name, " +
            "(select cb.amount from CategoryBudget cb where cb.category = c and cb.budget.year = :budgetYear and cb.budget.month = :budgetMonth), " +
            "(select sum(t.amount) from Transaction t where t.category = c and t.bookingDate >= :intervalStart and t.bookingDate <= :intervalEnd), " +
                "(select mc from MasterCategory mc where mc.id = c.masterCategory.id) " +
            ") " +
            "from Category c " +
            "where c.masterCategory.user = :user")
    List<BudgetCategory> findAllBudgetCategories(
            @Param("user") User user,
            @Param("intervalStart") LocalDateTime intervalStart,
            @Param("intervalEnd") LocalDateTime intervalEnd,
            @Param("budgetYear") int budgetYear,
            @Param("budgetMonth") int budgetMonth
    );
}
