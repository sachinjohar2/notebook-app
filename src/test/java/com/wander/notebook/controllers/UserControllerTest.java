package com.wander.notebook.controllers;

import com.wander.notebook.model.User;
import com.wander.notebook.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {
        User user = new User("bob", "bob", "bob@gmail.com");

        Mockito.when(userService.save(any()))
               .thenReturn(user);
    }

    @Test
    public void testSignUp() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/sign-up")
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .content("{ \"id\":\"1\",\n"
                                                                       + "\t\"username\":\"bob\",\n"
                                                                       + "\t\"name\":\"bob\",\n"
                                                                       + "\t\"email\":\"bob@gmail.com\",\n"
                                                                       + "\t\"password\":\"password\"}")
                                                              .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }
}
