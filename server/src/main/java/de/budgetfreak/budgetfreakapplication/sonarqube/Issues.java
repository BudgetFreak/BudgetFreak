package de.budgetfreak.budgetfreakapplication.sonarqube;

public class Issues {
    public void issue() {
        boolean variable_with_naming_issue = true;
        if (!variable_with_naming_issue) {
            throw new RuntimeException("No one should do this");
        }
    }
}
