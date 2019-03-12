package com.wander.notebook.request;

import java.util.List;

import com.wander.notebook.model.User;

public class GenericQuery<T> {

    List<T> data;

    public GenericQuery() {
    }

    public GenericQuery(final List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(final List<T> data) {
        this.data = data;
    }
}
