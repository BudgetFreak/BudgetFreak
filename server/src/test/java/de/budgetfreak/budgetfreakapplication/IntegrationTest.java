package de.budgetfreak.budgetfreakapplication;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BudgetfreakapplicationApplication.class)
@ActiveProfiles("dev")
public abstract class IntegrationTest {
}
