package com.wander.notebook.repositories;

import java.util.List;

import com.wander.notebook.model.Note;
import com.wander.notebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByUserId(User user);

    Note findById(long id);

    List<Note> findByUserOrderByUpdateTime(User user);
}
