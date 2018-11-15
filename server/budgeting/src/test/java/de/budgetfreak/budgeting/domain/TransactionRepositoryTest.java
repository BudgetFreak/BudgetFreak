package de.budgetfreak.budgeting.domain;

import de.budgetfreak.accounting.domain.Account;
import de.budgetfreak.accounting.domain.AccountRepository;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository testSubject;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MasterCategoryRepository masterCategoryRepository;

    @Autowired
    private PayeeRepository payeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldSaveNewMaterCategories() {
        User user = userRepository.save(new User().setName("Bob").setCurrency("€"));
        MasterCategory masterCategory = masterCategoryRepository.save(new MasterCategory().setName("mastercategory").setUser(user));
        Category category = categoryRepository.save(new Category().setName("category").setMasterCategory(masterCategory).setUser(user));
        Payee payee = payeeRepository.save(new Payee().setName("payee").setUser(user));
        Account account = accountRepository.save(new Account().setUser(user).setOnBudget(true).setDescription("account"));
        LocalDateTime bookingDate = LocalDateTime.now();

        Transaction transaction = new Transaction()
                .setAmount(new BigDecimal("1337.42"))
                .setBookingDate(bookingDate)
                .setDescription("description")
                .setCleared(true)
                .setPayee(payee)
                .setCategory(category)
                .setAccount(account);
        Long id = testSubject.save(transaction).getId();

        Optional<Transaction> savedTransactionOptional = testSubject.findById(id);
        assertThat(savedTransactionOptional).isPresent();
        Transaction savedTransaction = savedTransactionOptional.get();
        assertThat(savedTransaction.getAmount()).isEqualTo(new BigDecimal("1337.42"));
        assertThat(savedTransaction.getBookingDate()).isEqualTo(bookingDate);
        assertThat(savedTransaction.getDescription()).isEqualTo("description");
        assertThat(savedTransaction.isCleared()).isEqualTo(true);
        assertThat(savedTransaction.getPayee().getId()).isEqualTo(payee.getId());
        assertThat(savedTransaction.getCategory().getId()).isEqualTo(category.getId());
        assertThat(savedTransaction.getAccount().getId()).isEqualTo(account.getId());
    }
}