package springframework.library.service.impl;

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
    public Author getById(Long id) throws IllegalArgumentException {
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
    public Long count() {
        return dao.count();
    }

    @Override
    public void insert(String name, String surname) {
        Author author = dao.getByNameAndSurname(name, surname);
        if (author != null) {
            System.out.println("Такой автор уже существует в базе данных, ID = " + author.getId());
        } else {
            author = new Author(name, surname);
            dao.insert(author);
        }
    }

    @Override
    public void remove(Long authorId) throws IllegalArgumentException {
        Author author = getById(authorId);
        dao.remove(author);
    }
}
