package de.budgetfreak.usermanagement;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = BudgetfreakApplication.class)
@ActiveProfiles("test")
@DataJpaTest
@SqlConfig(encoding = "UTF-8")
public abstract class DatabaseTest {
}
