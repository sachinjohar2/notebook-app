package com.wander.notebook.controllers;

import com.wander.notebook.model.User;
import com.wander.notebook.repositories.UserRepository;
import com.wander.notebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:1900")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        userService.save(user);
    }
}


