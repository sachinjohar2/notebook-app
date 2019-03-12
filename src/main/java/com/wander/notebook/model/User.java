package com.wander.notebook.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username" , unique = true)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name="email", unique = true)
    private String email;

    @Column
    private String password;


    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("userId")
    @JsonIgnore
    Set<Notebook> notebooks;

    public User(final Integer id, final String username, final String name, final String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public User(final String username, final String name, final String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public Set<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(final Set<Notebook> notebooks) {
        this.notebooks = notebooks;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
