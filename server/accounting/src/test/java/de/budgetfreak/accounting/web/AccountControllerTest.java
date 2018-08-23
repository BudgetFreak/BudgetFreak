package de.budgetfreak.accounting.web;

import de.budgetfreak.accounting.AccountTestUtils;
import de.budgetfreak.accounting.UserTestUtils;
import de.budgetfreak.accounting.domain.Account;
import de.budgetfreak.accounting.service.AccountService;
import de.budgetfreak.utils.webtests.JsonHelper;
import de.budgetfreak.usermanagement.domain.User;
import org.assertj.core.api.Assertions;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
@Import(value = AccountResourceAssembler.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldListAllAccounts() throws Exception {
        User bob = UserTestUtils.createBob();
        when(accountServiceMock.list(bob.getId())).thenReturn(AccountTestUtils.createAccounts(UserTestUtils.createBob()));

        final MockHttpServletRequestBuilder requestBuilder = get("/users/{userId}/accounts", bob.getId()).accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final PagedResources<AccountResource> accountResources = JsonHelper.fromPagedResourceJson(response.getContentAsString(), AccountResource.class);
        Assertions.assertThat(accountResources).hasSize(2);
        Assertions.assertThat(accountResources).extracting("description").containsExactly("Checkings", "Savings");
    }

    @Test
    public void shouldGetOneAccount() throws Exception {
        User bob = UserTestUtils.createBob();
        Account savingsAccount = AccountTestUtils.createSavingsAccount(bob);
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