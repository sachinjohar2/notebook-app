package com.wander.notebook.controllers;

import java.util.List;

import com.wander.notebook.model.Notebook;
import com.wander.notebook.model.User;
import com.wander.notebook.services.NotebookService;
import com.wander.notebook.services.UserService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notebook")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private UserService userService;


    @PostMapping
    public void addNotebook(@RequestBody List<Notebook> notebook){
        notebookService.saveAll(notebook);
    }

    @GetMapping
    public List<Notebook> getNotebook(@RequestParam String username) {
        User user = userService.findByUsername(username);
        return notebookService.findByUser(user);
    }

    @PutMapping({"/{id}"})
    public void editNotebook(@PathVariable long id, @RequestBody Notebook notebook){
        Notebook existingNotebook = notebookService.findById(id);
        Assert.notNull(existingNotebook, "Notebook not found");
        existingNotebook.setDescription(notebook.getDescription());
        notebookService.save(existingNotebook);
    }

    @DeleteMapping("/{id}")
    public void deleteNotbook(@PathVariable long id){
        Notebook notebookToDelete = notebookService.findById(id);
        notebookService.deleteNotebook(notebookToDelete);
    }

}
