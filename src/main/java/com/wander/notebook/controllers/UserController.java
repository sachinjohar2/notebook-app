package com.wander.notebook.controllers;

import com.wander.notebook.model.User;
import com.wander.notebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/validate", method= RequestMethod.GET)
    public String getConnection(){
        return "Connected";
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        userService.save(user);
    }
}


