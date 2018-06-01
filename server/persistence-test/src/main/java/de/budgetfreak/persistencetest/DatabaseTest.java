package de.budgetfreak.persistencetest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Base class for testing the persistence layer. Activates the profile "test".
 * Use {@code @ContextConfiguration} to configure the Spring context to load.
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@SqlConfig(encoding = "UTF-8")
public abstract class DatabaseTest {
}
