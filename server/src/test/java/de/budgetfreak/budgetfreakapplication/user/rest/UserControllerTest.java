package de.budgetfreak.budgetfreakapplication.user.rest;

import de.budgetfreak.budgetfreakapplication.TestDatabaseContext;
import de.budgetfreak.budgetfreakapplication.user.domain.User;
import de.budgetfreak.budgetfreakapplication.user.domain.UserService;
import de.budgetfreak.budgetfreakapplication.utils.JsonHelper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.JsonExpectationsHelper;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = UserController.class)
@ComponentScan(basePackages = "de.budgetfreak.budgetfreakapplication.user")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserResourceAssambler userResourceAssambler;

    @MockBean
    private UserService userService;

    @Test
    public void getAllUser() throws Exception {

        //init
        List<User> userList = new ArrayList<>();
        userList.add(new User("John"));
        userList.add(new User("Eve"));

        //when
        when(userService.getAllUser()).thenReturn(userList);

        RequestBuilder requestBuilder = get("/api/users").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        PagedResources<UserResource> userResources = JsonHelper.fromPagedResourceJson(contentAsString, UserResource.class);

        //assert
        Assertions.assertThat(userResources).extracting("name").containsExactly("John", "Eve");
    }

    @Test
    public void createUser() throws Exception {
        //init
        final String userName = "Jenny";
        User testUser = new User(userName);

        //when
        when(userService.createUser(anyString())).thenReturn(testUser);

        MvcResult mvcResult = this.mockMvc
                .perform(
                        post("/api")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .param("name", userName)
                                .content(JsonHelper.toJsonWithoutLinks(new UserResource(userName)))
                ).andExpect(
                        status().isOk()
                ).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        UserResource userResource = JsonHelper.fromJson(contentAsString, UserResource.class);

        //assert
        Assertions.assertThat(userResource.getName()).isEqualTo(userName);

    }
}