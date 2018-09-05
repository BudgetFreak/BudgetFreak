package de.budgetfreak.budgeting.domain;

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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan("de.budgetfreak")
@EnableJpaRepositories("de.budgetfreak")
public class PayeeRepositoryTest {

    @Autowired
    private PayeeRepository testSubject;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveNewMaterCategories() {
        User user = userRepository.save(new User().setName("Bob").setCurrency("€"));

        Payee payee = new Payee().setName("payee's name").setUser(user);
        Long payeeId = testSubject.save(payee).getId();

        Optional<Payee> savedPayeeOptional = testSubject.findById(payeeId);
        assertThat(savedPayeeOptional).isPresent();
        Payee savedPayee = savedPayeeOptional.get();
        assertThat(savedPayee.getName()).isEqualTo("payee's name");
        assertThat(savedPayee.getUser().getId()).isEqualTo(user.getId());
    }
}