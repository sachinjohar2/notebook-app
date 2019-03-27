package com.wander.notebook.services;

import com.wander.notebook.model.User;
import com.wander.notebook.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                                               mockBCryptPasswordEncoder);
        user = new User("abhisheksingh", "abhishek", "abhisingh@gmail.com");

        Mockito.when(mockUserRepository.save(any()))
               .thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString()))
               .thenReturn(user);
        Mockito.when(mockUserRepository.findByUsername(anyString()))
               .thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {

        final User result = userServiceUnderTest.findByEmail("abhisingh@gmail.com");

        // Verify the results
        assertEquals("abhisheksingh", result.getUsername());
        assertEquals("abhishek", result.getName());
        assertEquals("abhisingh@gmail.com", result.getEmail());
    }

    @Test
    public void testSaveUser() {

        User result = userServiceUnderTest.save(new User("abhisheksingh", "abhishek", "abhisingh@gmail.com"));

        assertEquals("abhisheksingh", result.getUsername());
        assertEquals("abhishek", result.getName());
        assertEquals("abhisingh@gmail.com", result.getEmail());
    }

    @Test
    public void testFindUserByUsername(){
        final User user = userServiceUnderTest.findByUsername("abhishekSingh");

        assertEquals("abhisheksingh", user.getUsername());
        assertEquals("abhishek", user.getName());
        assertEquals("abhisingh@gmail.com", user.getEmail());
    }
}

