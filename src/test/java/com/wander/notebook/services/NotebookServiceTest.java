package com.wander.notebook.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.wander.notebook.model.Notebook;
import com.wander.notebook.model.User;
import com.wander.notebook.repositories.NotebookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

public class NotebookServiceTest {

    @Mock
    private NotebookRepository notebookRepository;

    private NotebookService notebookService;
    private Notebook notebook;

    @Before
    public void setUp(){
        initMocks(this);
        notebookService = new NotebookService(notebookRepository);

        notebook = new Notebook("Help Page", "This includes help content", LocalDateTime.MAX, LocalDateTime.MAX);

        Mockito.when(notebookRepository.findByUserId(any()))
               .thenReturn(Arrays.asList(notebook));

    }

    @Test
    public void testFindByUser(){
        final List<Notebook> notebooks = notebookService.findByUser(new User());

        assertEquals(1, notebooks.size());
        assertEquals("Help Page", notebooks.get(0).getTitle());
        assertEquals("This includes help content", notebooks.get(0).getDescription());

    }

}
