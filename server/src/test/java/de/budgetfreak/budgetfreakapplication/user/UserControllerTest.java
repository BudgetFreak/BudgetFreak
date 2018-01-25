package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.BudgetfreakapplicationApplication;
import de.budgetfreak.budgetfreakapplication.JsonHelper;
import de.budgetfreak.budgetfreakapplication.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BudgetfreakapplicationApplication.class)
@ActiveProfiles("test")
@WebMvcTest(value = UserController.class)
@Import(value = UserResourceAssembler.class)
public class UserControllerTest {

    @MockBean
    private UserService userServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shoudListAllUser() throws Exception {
        when(userServiceMock.list()).thenAnswer(this::createUsers);

        final MockHttpServletRequestBuilder requestBuilder = get("/users").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final PagedResources<UserResource> userResources = JsonHelper.fromPagedResourceJson(response.getContentAsString(), UserResource.class);
        assertThat(userResources).hasSize(2);
        assertThat(userResources).extracting("name").containsExactly("Bob", "Jane");
    }

    @Test
    public void shouldCreateUser() throws Exception {
        final UserResource userResource = new UserResource("Martha", "€");
        when(userServiceMock.create(anyString(), anyString())).thenAnswer(invocation ->
                new User().setId(1L)
                    .setName(invocation.getArgument(0))
                    .setCurrency(invocation.getArgument(1))
        );

        final MockHttpServletRequestBuilder requestBuilder = post("/users").accept(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(userResource)).contentType(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final UserResource result = JsonHelper.fromJson(response.getContentAsString(), UserResource.class);
        assertThat(result.getName()).isEqualTo("Martha");
        assertThat(result.getCurrency()).isEqualTo("€");

        verify(userServiceMock).create("Martha", "€");
    }

    private List<User> createUsers(InvocationOnMock invocationOnMock) {
        return Arrays.asList(
                new User().setId(1L).setName("Bob").setCurrency("€"),
                new User().setId(2L).setName("Jane").setCurrency("$")
        );
    }
}