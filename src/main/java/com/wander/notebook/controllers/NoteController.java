package com.wander.notebook.controllers;

import java.security.Principal;
import java.util.Arrays;
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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Note> createNote(@RequestBody @Valid Note note, Authentication authentication){
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if(user == null){
            throw new IllegalArgumentException("Failed to find user with username "+ username + " passed as argument");
        }

        note.setUser(user);
        return new ResponseEntity<>(noteService.save(note), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getUserNotes(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        if(user == null){
            throw new IllegalArgumentException("Failed to find user with username "+ username + " passed as argument");
        }
        return new ResponseEntity<>(noteService.findByUser(user), HttpStatus.OK);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Note> updateNote(@PathVariable long id, @RequestBody @Valid Note note){

        return new ResponseEntity<>(noteService.updateNote(id, note), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteNote(@PathVariable long id){
        noteService.deleteNote(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);

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
