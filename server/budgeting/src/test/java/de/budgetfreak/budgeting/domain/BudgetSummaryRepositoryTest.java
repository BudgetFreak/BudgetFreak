package de.budgetfreak.budgeting.domain;

import de.budgetfreak.accounting.domain.Account;
import de.budgetfreak.accounting.domain.AccountRepository;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
public class BudgetSummaryRepositoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(BudgetSummaryRepositoryTest.class);

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
    private CategoryBudgetRepository categoryBudgetRepository;

    @Autowired
    private BudgetSummaryRepository testSubject;

    private CategoryBudget createCategoryBudget(Budget budget, Category categoryGames, BigDecimal amount) {
        return categoryBudgetRepository.save(new CategoryBudget().setCategory(categoryGames).setAmount(amount).setBudget(budget));
    }

    @Test
    public void shouldSelectBugetCategory() {
        User user = createUser();
        Budget budget = createBudget(user, 2018, 8);
        MasterCategory masterCategory = createMasterCategory(user, "Daily Spendings");
        Category categoryGroceries = createCategory(masterCategory, "Groceries");
        Category categoryGames = createCategory(masterCategory, "Games");
        Payee payee = createPayee(user);
        Account account = createAccount(user);

        CategoryBudget categoryBudgetGames = createCategoryBudget(budget, categoryGames, new BigDecimal(200));
        CategoryBudget categoryBudgetGroceries = createCategoryBudget(budget, categoryGroceries, new BigDecimal(100));

        // Games
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGames).setAmount(new BigDecimal("50.00")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGames).setAmount(new BigDecimal("70.00")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGames).setAmount(new BigDecimal("30.00")));

        // Groceries
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGroceries).setAmount(new BigDecimal("20.00")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGroceries).setAmount(new BigDecimal("10.00")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGroceries).setAmount(new BigDecimal("5.50")));
        transactionRepository.save(createTransaction(payee, account).setCategory(categoryGroceries).setAmount(new BigDecimal("54.50")));

        List<BudgetCategory> budgetCategories = testSubject.findAllBudgetCategories(user, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), 2018, 8);
//        assertThat(budgetCategories).hasSize(2);
//        assertThat(budgetCategories).filteredOn(budgetCategory -> budgetCategory.getName().equals("Games")).extracting(BudgetCategory::getBudget).first().isEqualTo(new BigDecimal("150.00"));
//        assertThat(budgetCategories).filteredOn(budgetCategory -> budgetCategory.getName().equals("Groceries")).extracting(BudgetCategory::getBudget).first().isEqualTo(new BigDecimal("90.00"));

        budgetCategories.forEach(budgetCategory -> LOG.info("BudgetCategory: {}", budgetCategory));
    }

    private Account createAccount(User user) {
        return accountRepository.save(new Account("Account", true, user));
    }

    private Payee createPayee(User user) {
        return payeeRepository.save(new Payee().setName("Payee").setUser(user));
    }

    private User createUser() {
        return userRepository.save(new User().setName("Bob").setCurrency("€"));
    }

    private Budget createBudget(User user, int year, int month) {
        Budget budget = new Budget().setYear(year).setMonth(month).setUser(user);
        return budgetRepository.save(budget);
    }

    private MasterCategory createMasterCategory(User user, String name) {
        return masterCategoryRepository.save(new MasterCategory().setName(name).setUser(user));
    }

    private Category createCategory(MasterCategory masterCategory, String name) {
        Category category = new Category().setName(name).setMasterCategory(masterCategory).setUser(masterCategory.getUser());
        return categoryRepository.save(category);
    }

    private Transaction createTransaction(Payee payee, Account account) {
        return new Transaction().setAccount(account).setCleared(true).setPayee(payee).setBookingDate(LocalDateTime.now());
    }
}