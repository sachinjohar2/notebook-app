package com.wander.notebook.services;

import java.util.List;

import com.wander.notebook.model.Note;
import com.wander.notebook.model.User;
import com.wander.notebook.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public List<Note> saveAll(List<Note> notes) {
        return noteRepository.saveAll(notes);
    }

    public List<Note> findByUser(User user) {
        List<Note> notes = noteRepository.findByUserOrderByUpdateTime(user);

        if(notes.isEmpty()){
            throw new IllegalArgumentException("No notes found for user with username "+ user.getUsername());
        }

        return notes;
    }

    public Note findById(long id) {
        return noteRepository.findById(id);
    }


    public void deleteNote(long id) {
        Note existingNote = noteRepository.findById(id);

        if (existingNote == null) {
            throw new IllegalArgumentException("Failed to find note with id : " + id);
        }

        noteRepository.delete(existingNote);
    }


    public Note updateNote(long id, Note newNote) {
        Note existingNote = findById(id);

        if (existingNote == null) {
            throw new IllegalArgumentException("Failed to find note with id : " + id);
        }

        if (!existingNote.getTitle().equals(newNote.getTitle())) {
            existingNote.setTitle(newNote.getTitle());
        }

        if (!existingNote.getDescription().equals(newNote.getDescription())) {
            existingNote.setDescription(newNote.getDescription());
        }

        return noteRepository.save(existingNote);
    }
}
