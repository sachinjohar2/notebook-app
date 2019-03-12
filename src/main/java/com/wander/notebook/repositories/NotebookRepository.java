package com.wander.notebook.repositories;

import java.util.List;

import com.wander.notebook.model.Notebook;
import com.wander.notebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, Integer> {

    public List<Notebook> findByUserId(User user);

    Notebook findById(long id);
}
