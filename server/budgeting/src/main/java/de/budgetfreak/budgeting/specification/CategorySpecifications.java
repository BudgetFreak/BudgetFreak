package de.budgetfreak.budgeting.specification;

import de.budgetfreak.budgeting.domain.Category;
import de.budgetfreak.budgeting.domain.Category_;
import de.budgetfreak.budgeting.domain.MasterCategory_;
import de.budgetfreak.usermanagement.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * {@link Specification}s for {@link Category}-entities.
 */
@Component
public class CategorySpecifications {

    public Specification<Category> byUser(User user) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Category_.masterCategory).get(MasterCategory_.user), user);
    }
}
