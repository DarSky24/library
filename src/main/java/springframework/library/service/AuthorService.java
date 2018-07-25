package springframework.library.service;

import springframework.library.domain.Author;

import java.util.List;

public interface AuthorService {

    Author getById(Long id);

    List<Author> getAll();

    Long count();

    void insert(String name, String surname);

    void remove (Long authorId);
}
