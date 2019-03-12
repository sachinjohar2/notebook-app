package com.wander.notebook.services;

import java.util.List;

import com.wander.notebook.model.Notebook;
import com.wander.notebook.model.User;
import com.wander.notebook.repositories.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotebookService {

    @Autowired
    private NotebookRepository notebookRepository;

    public NotebookService(NotebookRepository notebookRepository){
        this.notebookRepository = notebookRepository;
    }

    public void save(Notebook notebook){
        notebookRepository.save(notebook);
    }

    public List<Notebook> getAll(){
        return notebookRepository.findAll();
    }

    public List<Notebook> saveAll(List<Notebook> notebooks){
        return notebookRepository.saveAll(notebooks);
    }

    public List<Notebook> findByUser(User user){
        List<Notebook> notebooks = notebookRepository.findByUserId(user);

        return notebooks;
    }

    public Notebook findById(long id){
        return notebookRepository.findById(id);
    }

    public void deleteNotebook(Notebook notebook){
        notebookRepository.delete(notebook);
    }
}
