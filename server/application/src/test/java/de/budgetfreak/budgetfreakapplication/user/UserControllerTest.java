package de.budgetfreak.budgetfreakapplication.user;

import de.budgetfreak.budgetfreakapplication.BudgetfreakApplication;
import de.budgetfreak.budgetfreakapplication.JsonHelper;
import de.budgetfreak.budgetfreakapplication.user.domain.User;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BudgetfreakApplication.class)
@ActiveProfiles("test")
@WebMvcTest(value = UserController.class)
@Import(value = UserResourceAssembler.class)
public class UserControllerTest {

    @MockBean
    private UserService userServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldListAllUser() throws Exception {
        when(userServiceMock.list()).thenAnswer(invocation -> UserTestUtils.createBobAndJane());

        final MockHttpServletRequestBuilder requestBuilder = get("/users").accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final PagedResources<UserResource> userResources = JsonHelper.fromPagedResourceJson(response.getContentAsString(), UserResource.class);
        assertThat(userResources).hasSize(2);
        assertThat(userResources).extracting("name").containsExactly("Bob", "Jane");
    }

    @Test
    public void shouldGetOneUser() throws Exception {
        when(userServiceMock.get(anyLong())).thenReturn(UserTestUtils.createBob());

        final MockHttpServletRequestBuilder requestBuilder = get("/users/{id}", 1L).accept(MediaType.APPLICATION_JSON);
        final MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        final UserResource userResource = JsonHelper.fromJson(response.getContentAsString(), UserResource.class);
        assertThat(userResource.getName()).isEqualTo("Bob");
        assertThat(userResource.getCurrency()).isEqualTo("€");
    }

    @Test
    public void shouldCreateUser() throws Exception {
        final String name = "Martha";
        final String currency = "€";
        final UserResource userResource = new UserResource(name, currency);
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
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getCurrency()).isEqualTo(currency);

        verify(userServiceMock).create(name, currency);
    }

}