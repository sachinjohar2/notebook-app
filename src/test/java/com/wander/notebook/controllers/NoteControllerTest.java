package com.wander.notebook.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import com.wander.notebook.model.Note;
import com.wander.notebook.model.User;
import com.wander.notebook.services.NoteService;
import com.wander.notebook.services.UserDetailsServiceImpl;
import com.wander.notebook.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Ignore;
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

import static com.wander.notebook.security.SecurityConstants.EXPIRATION_TIME;
import static com.wander.notebook.security.SecurityConstants.SECRET;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = NoteController.class, secure = false    )
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() {

        Note note = new Note("Help Page",
                             "This includes help content",
                             LocalDateTime.MAX,
                             LocalDateTime.MAX);
        User user = new User( "abhisheksingh", "abhishek", "abhisingh@gmail.com");
        Mockito.when(userService.findByUsername(any())).thenReturn(user);
        Mockito.when(noteService.saveAll(any())).thenReturn(Arrays.asList(note));
        Mockito.when(noteService.findByUser(any())).thenReturn(Arrays.asList(note));

    }

    @Ignore
    @Test
    public void testGetNotebookByUsername() throws Exception {

        String jwt = Jwts.builder()
                         .setSubject("user")
                         .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                         .signWith(SignatureAlgorithm.HS512, SECRET)
                         .compact();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notebook")
                                                              .header("Authorization", jwt)
                                                              .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        assertEquals(
                "[{\"id\":null,\"title\":\"Help Page\",\"description\":\"This includes help content\",\"creationTime\":\"+999999999-12-31T23:59:59.999999999\",\"updateTime\":\"+999999999-12-31T23:59:59.999999999\"}]",
                result.getResponse().getContentAsString());
    }
}
