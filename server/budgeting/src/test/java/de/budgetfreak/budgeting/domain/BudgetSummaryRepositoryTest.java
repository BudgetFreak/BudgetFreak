package de.budgetfreak.budgeting.domain;

import de.budgetfreak.accounting.domain.Account;
import de.budgetfreak.accounting.domain.AccountRepository;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
public class BudgetSummaryRepositoryTest {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MasterCategoryRepository masterCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PayeeRepository payeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BudgetSummaryRepository testSubject;

    @Test
    public void shouldSelectBugetCategory() {
        User user = userRepository.save(new User().setName("Bob").setCurrency("€"));

        Budget budget = new Budget().setYear(2018).setMonth(8).setUser(user);
        Long budgetId = budgetRepository.save(budget).getId();

        MasterCategory masterCategory = masterCategoryRepository.save(new MasterCategory().setName("Daily Spendings").setUser(user));

        Category categoryGroceries = new Category().setName("Groceries").setMasterCategory(masterCategory);
        long categoryIdGroceries = categoryRepository.save(categoryGroceries).getId();

        Category categoryGames = new Category().setName("Games").setMasterCategory(masterCategory);
        long categoryIdGames = categoryRepository.save(categoryGames).getId();

        Payee payee = payeeRepository.save(new Payee().setName("Payee").setUser(user));

        Account account = accountRepository.save(new Account("Account", true, user));

        // Games
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGames).setAmount(new BigDecimal("50.00")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGames).setAmount(new BigDecimal("70.00")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGames).setAmount(new BigDecimal("30.00")));

        // Groceries
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGroceries).setAmount(new BigDecimal("20.00")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGroceries).setAmount(new BigDecimal("10.00")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGroceries).setAmount(new BigDecimal("5.50")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGroceries).setAmount(new BigDecimal("54.50")));

        List<BudgetCategory> budgetCategories = testSubject.findAllBudgetCategories(user);
        assertThat(budgetCategories).hasSize(2);
        assertThat(budgetCategories).filteredOn(budgetCategory -> budgetCategory.getName().equals("Games")).extracting(BudgetCategory::getBalance).first().isEqualTo(new BigDecimal("150.00"));
        assertThat(budgetCategories).filteredOn(budgetCategory -> budgetCategory.getName().equals("Groceries")).extracting(BudgetCategory::getBalance).first().isEqualTo(new BigDecimal("90.00"));
    }

    private Transaction createTransaction(Payee payee, Account account) {
        return new Transaction().setAccount(account).setCleared(true).setPayee(payee).setBookingDate(new Date());
    }
}