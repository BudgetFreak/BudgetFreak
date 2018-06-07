package de.budgetfreak.application.account;

import de.budgetfreak.application.BudgetfreakApplication;
import de.budgetfreak.application.JsonHelper;
import de.budgetfreak.application.account.domain.Account;
import de.budgetfreak.usermanagement.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static de.budgetfreak.application.account.AccountTestUtils.createAccounts;
import static de.budgetfreak.application.account.AccountTestUtils.createSavingsAccount;
import static de.budgetfreak.application.user.UserTestUtils.createBob;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BudgetfreakApplication.class)
@ActiveProfiles("test")
@WebMvcTest(value = AccountController.class)
@Import(value = AccountResourceAssembler.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldListAllAccounts() throws Exception {
        User bob = createBob();
        when(accountServiceMock.list(bob.getId())).thenReturn(createAccounts(createBob()));

        final MockHttpServletRequestBuilder requestBuilder = get("/users/{userId}/accounts", bob.getId()).accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final PagedResources<AccountResource> userResources = JsonHelper.fromPagedResourceJson(response.getContentAsString(), AccountResource.class);
        assertThat(userResources).hasSize(2);
        assertThat(userResources).extracting("description").containsExactly("Checkings", "Savings");
    }

    @Test
    public void shouldGetOneAccount() throws Exception {
        User bob = createBob();
        Account savingsAccount = createSavingsAccount(bob);
        when(accountServiceMock.get(savingsAccount.getId())).thenReturn(savingsAccount);

        final MockHttpServletRequestBuilder requestBuilder = get("/users/{userId}/accounts/{id}", bob.getId(), savingsAccount.getId()).accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final AccountResource userResource = JsonHelper.fromJson(response.getContentAsString(), AccountResource.class);
        assertThat(userResource.getDescription()).isEqualTo(savingsAccount.getDescription());
        assertThat(userResource.getOnBudget()).isEqualTo(savingsAccount.isOnBudget());
    }

    @Test
    public void shouldCreateAccount() throws Exception {
        String description = "Cash";
        boolean onBudget = true;
        long userId = 42L;
        AccountResource accountResource = new AccountResource(description, onBudget);
        when(accountServiceMock.create(anyLong(), anyString(), anyBoolean())).thenAnswer(invocation ->
                new Account(invocation.getArgument(1), invocation.getArgument(2), new User().setId(invocation.getArgument(0))).setId(1L));

        final MockHttpServletRequestBuilder requestBuilder = post("/users/{userId}/accounts", userId).accept(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(accountResource)).contentType(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        AccountResource result = JsonHelper.fromJson(response.getContentAsString(), AccountResource.class);
        assertThat(result.getDescription()).isEqualTo(description);
        assertThat(result.getOnBudget()).isEqualTo(onBudget);

        verify(accountServiceMock).create(userId, description, onBudget);
    }
}