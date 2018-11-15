package de.budgetfreak.budgeting.domain;

/**
 * Type of a {@link Category}.
 */
public enum CategoryType {

    /**
     * User defined category.
     */
    USER_DEFINED("USER_DEFINED"),

    /**
     * Income available in the month of it's booking date.
     */
    INCOME_THIS_MONTH("INCOME_THIS_MONTH"),

    /**
     * Income available in the month after it's booking date.
     */
    INCOME_NEXT_MONTH("INCOME_NEXT_MONTH"),

    ;

    private String key;

    CategoryType(String key) {
        this.key = key;
    }


    public String getKey() {
        return key;
    }
}
