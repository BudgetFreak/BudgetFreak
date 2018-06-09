package de.budgetfreak.usermanagement;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
