package com.wander.notebook.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.wander.notebook.model.Note;
import com.wander.notebook.model.User;
import com.wander.notebook.services.NoteService;
import com.wander.notebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notebook")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;



    @PostMapping
    public Note createNote(@RequestBody @Valid Note note, Authentication authentication){
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if(user == null){
            throw new IllegalArgumentException("Failed to find user with username "+ username + " passed as argument");
        }

        note.setUser(user);
        return noteService.save(note);
    }

    @GetMapping
    public List<Note> getUserNotes(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if(user == null){
            throw new IllegalArgumentException("Failed to find user with username "+ username + " passed as argument");
        }
        return noteService.findByUser(user);
    }

    @PutMapping({"/{id}"})
    public Note updateNote(@PathVariable long id, @RequestBody @Valid Note note){

        return noteService.updateNote(id, note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable long id){

        noteService.deleteNote(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
