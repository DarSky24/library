package springframework.library.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import springframework.library.dao.GenreDAO;
import springframework.library.domain.Genre;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenreServiceImplTest {

    @Mock
    private GenreDAO dao;
    @InjectMocks
    private GenreServiceImpl service;

    @Test(expected = IllegalArgumentException.class)
    public void getByIdWhenIdNotExist() {
        service.getById(2L);
    }

    @Test
    public void getByIdWhenIdExist() {
        when(dao.getById(1L)).thenReturn(new Genre());
        service.getById(1L);
    }

    @Test
    public void getAll() {
        service.getAll();
        verify(dao).getAll();
    }

    @Test
    public void count() {
        service.count();
        verify(dao).count();
    }

    @Test
    public void insertUnique() {
        when(dao.getByName("test genre")).thenReturn(null);
        service.insert("test genre");
        verify(dao).insert(refEq(new Genre("test genre")));

    }

    @Test
    public void insertDuplicate() {
        when(dao.getByName("test genre")).thenReturn(new Genre("test genre"));
        service.insert("test genre");
        verify(dao, times(0)).insert(refEq(new Genre("test genre")));

    }

    @Test
    public void removeExist() {
//        Genre genre = new Genre(1, "test genre");
//        when(dao.getById(1)).thenReturn(genre);
//        service.remove(1);
//        verify(dao).remove(refEq(genre));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNotExist() {
//        Genre genre = new Genre(1, "test genre");
//        service.remove(1);
//        verify(dao, times(0)).remove(refEq(genre));
    }
}