package com.wander.notebook.responses;

import com.wander.notebook.model.User;

public class UserResponse {

    private Long id;
    private String name;
    private String username;

    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
