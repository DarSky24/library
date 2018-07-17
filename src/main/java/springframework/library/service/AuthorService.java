package springframework.library.service;

import springframework.library.domain.Author;

import java.util.List;

public interface AuthorService {

    Author getById(int id);

    List<Author> getAll();

    int count();

    void insert(String name, String surname);

    void remove (int authorId);
}
