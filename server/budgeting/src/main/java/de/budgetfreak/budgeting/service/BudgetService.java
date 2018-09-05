package de.budgetfreak.budgeting.service;

import de.budgetfreak.budgeting.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for managing budgets.
 */
@Service
public class BudgetService {

    private CategoryRepository categoryRepository;

    @Autowired
    public BudgetService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
