package com.wander.notebook.request;

import java.util.List;

import com.wander.notebook.model.User;

public class UserQuery {

    List<User> data;

    public UserQuery() {
    }

    public UserQuery(final List<User> users) {
        this.data = users;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(final List<User> data) {
        this.data = data;
    }
}