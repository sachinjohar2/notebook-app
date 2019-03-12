package com.wander.notebook.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;

import com.wander.notebook.model.Notebook;
import com.wander.notebook.model.User;
import com.wander.notebook.services.NotebookService;
import com.wander.notebook.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NotebookController.class, secure = false)
public class NotebookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotebookService notebookService;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {

        Notebook notebook = new Notebook("Help Page",
                                         "This includes help content",
                                         LocalDateTime.MAX,
                                         LocalDateTime.MAX);
        User user = new User(1L, "abhisheksingh", "abhishek", "abhisingh@gmail.com");
        Mockito.when(userService.findByUsername(any())).thenReturn(user);
        Mockito.when(notebookService.saveAll(any())).thenReturn(Arrays.asList(notebook));
        Mockito.when(notebookService.findByUser(any())).thenReturn(Arrays.asList(notebook));

    }

    @Test
    public void testGetNotebookByUsername() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notebook?username=abhishek")
                                                              .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        assertEquals(
                "[{\"id\":null,\"title\":\"Help Page\",\"description\":\"This includes help content\",\"creationTime\":\"+999999999-12-31T23:59:59.999999999\",\"updateTime\":\"+999999999-12-31T23:59:59.999999999\"}]",
                result.getResponse().getContentAsString());
    }
}
