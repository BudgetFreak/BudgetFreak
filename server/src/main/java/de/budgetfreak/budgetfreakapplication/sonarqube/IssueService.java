package de.budgetfreak.budgetfreakapplication.sonarqube;

import org.springframework.stereotype.Service;

@Service
public class IssueService {

    public void issue() {
        boolean variable_with_naming_issue = true;
        if (!variable_with_naming_issue) {
            throw new RuntimeException("No one should do this");
        }
    }
}
