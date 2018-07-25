package springframework.library.dao;

import springframework.library.domain.Author;
import springframework.library.domain.Book;
import springframework.library.domain.Genre;

import java.util.List;

public interface BookDAO {

    Book getById(Long id);

    List<Book> getByTitle(String title);

    Book getByParams(String title, Genre genre, Author author);

    List<Book> getAll();

    Long count();

    void insert(Book book);

    void remove (Book book);
}
