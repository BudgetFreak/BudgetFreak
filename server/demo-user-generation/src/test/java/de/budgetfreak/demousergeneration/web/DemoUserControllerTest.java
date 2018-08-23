package de.budgetfreak.demousergeneration.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import de.budgetfreak.accounting.service.AccountService;
import de.budgetfreak.testutils.web.JsonHelper;
import de.budgetfreak.usermanagement.domain.User;
import de.budgetfreak.usermanagement.service.UserService;
import de.budgetfreak.usermanagement.web.UserResource;
import de.budgetfreak.usermanagement.web.UserResourceAssembler;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DemoUserController.class)
public class DemoUserControllerTest {

    @MockBean
    private UserService userServiceMock;

    @MockBean
    private AccountService accountServiceMock;

    @MockBean
    private UserResourceAssembler userResourceAssemblerMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateUserWith3Accounts() throws Exception {

        User user = createUser();
        DemoUserNameResource demoUserNameResource = new DemoUserNameResource(user.getName());
        UserResource userResource = new UserResource(user.getName(), user.getCurrency());

        when(userServiceMock.create(user.getName(), user.getCurrency())).thenReturn(user);
        when(userResourceAssemblerMock.toResource(user)).thenReturn(userResource);

        MockHttpServletRequestBuilder request = post("/demouser").accept(MediaType.APPLICATION_JSON).content(JsonHelper.toJson(demoUserNameResource)).contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        UserResource userResourceResponse = JsonHelper.fromJson(response.getContentAsString(), UserResource.class);

        verify(accountServiceMock).create(user.getId(), "Household", true);
        verify(accountServiceMock).create(user.getId(), "Renovations", true);
        verify(accountServiceMock).create(user.getId(), "Budget for buying fun stuff", false);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(userResourceResponse.getName()).isEqualTo(user.getName());
        assertThat(userResourceResponse.getCurrency()).isEqualTo(user.getCurrency());
    }

    private User createUser() {
        User user = new User();
        user.setId(3L);
        user.setName("RegEx Rudi");
        user.setCurrency("?");
        return user;
    }
}
