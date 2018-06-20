package de.budgetfreak.usermanagement;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for the user-management-module.
 */
@ConfigurationProperties("usermanagement")
public class UserManagementProperties {

    private String testProperty;

    public String getTestProperty() {
        return testProperty;
    }

    public UserManagementProperties setTestProperty(String testProperty) {
        this.testProperty = testProperty;
        return this;
    }
}
