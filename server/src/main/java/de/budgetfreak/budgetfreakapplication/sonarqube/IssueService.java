package de.budgetfreak.budgetfreakapplication.sonarqube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class IssueService {
    private static final Logger log = LoggerFactory.getLogger(IssueService.class);

    public void issue() {
        boolean variable_with_naming_issue = true;
        if (!variable_with_naming_issue) {
            throw new RuntimeException("No one should do this");
        }
    }

    public void moreIssues() {
        try {
            Files.createTempFile("testing", "tmp");
        } catch (IOException e) {
            log.error("Could not create temp file.");
        }
    }
}
