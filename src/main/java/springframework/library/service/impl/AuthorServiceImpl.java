package springframework.library.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import springframework.library.dao.AuthorDAO;
import springframework.library.domain.Author;
import springframework.library.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDAO dao;

    public AuthorServiceImpl(AuthorDAO dao) {
        this.dao = dao;
    }

    @Override
    public Author getById(int id) {
        Author author = dao.getById(id);
        if (author == null) {
            throw new IllegalArgumentException("Не существует автора с ID = " + id);
        }
        return author;
    }

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public void insert(String name, String surname) {
        Author author = new Author(name, surname);
        dao.insert(author);

    }

    @Override
    public void remove(int authorId) throws IllegalArgumentException {
        Author author = getById(authorId);
        dao.remove(author);

    }
}
