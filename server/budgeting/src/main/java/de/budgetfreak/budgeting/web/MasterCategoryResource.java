package de.budgetfreak.budgeting.web;

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
