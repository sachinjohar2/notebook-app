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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username" , unique = true)
    @NotNull
    @Size(min = 6, max = 16)
    private String username;

    @Column(name = "name")
    @NotNull
    @Size(min = 3, max = 65)
    private String name;

    @Column(name="email", unique = true)
    @NotNull
    @Size(min = 6, max = 255)
    private String email;

    @Column
    @NotNull
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    @JsonIgnore
    Set<Note> notes;

    public User(final String username, final String name, final String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(final Set<Note> notes) {
        this.notes = notes;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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
