package de.budgetfreak.budgeting.web;

import de.budgetfreak.utils.web.ResourceEmbeddableSupport;

/**
 * Ressource representing a master category in a budget.
 */
public class MasterCategoryResource extends ResourceEmbeddableSupport {

    private String name;

    public MasterCategoryResource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
