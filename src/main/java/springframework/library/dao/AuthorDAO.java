package springframework.library.dao;

import springframework.library.domain.Author;

import java.util.List;

public interface AuthorDAO {

    Author getById(int id);

    Author getByNameAndSurname (String name, String surname);

    List<Author> getAll();

    int count();

    void insert(Author author);

    void remove (Author author);


}
