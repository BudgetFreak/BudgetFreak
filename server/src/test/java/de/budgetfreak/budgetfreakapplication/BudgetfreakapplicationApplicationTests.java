package de.budgetfreak.budgetfreakapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
						 DirtiesContextTestExecutionListener.class,
						 TransactionalTestExecutionListener.class,
						 DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:testdatabase.xml")
public class BudgetfreakapplicationApplicationTests {

	@Test
	public void contextLoads() {
	}

}
