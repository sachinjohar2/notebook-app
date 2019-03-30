package com.wander.notebook.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.wander.notebook.model.Note;
import com.wander.notebook.model.User;
import com.wander.notebook.repositories.NoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private NoteService noteService;
    private Note note;

    @Before
    public void setUp(){
        initMocks(this);
        noteService = new NoteService(noteRepository);

        note = new Note("Help Page", "This includes help content", LocalDateTime.MAX, LocalDateTime.MAX);

        Mockito.when(noteRepository.findByUserId(any()))
               .thenReturn(Arrays.asList(note));

    }

    @Test
    public void testFindByUser(){
        final List<Note> notes = noteService.findByUser(new User());

        assertEquals(1, notes.size());
        assertEquals("Help Page", notes.get(0).getTitle());
        assertEquals("This includes help content", notes.get(0).getDescription());

    }

}
