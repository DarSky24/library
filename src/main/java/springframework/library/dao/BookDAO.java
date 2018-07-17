package springframework.library.dao;

import springframework.library.domain.Book;

import java.util.List;

public interface BookDAO {

    Book getById(int id);

    List<Book> getByTitle(String title);

    Book getByParams(String title, int genreId, int authorId);

    List<Book> getAll();

    int count();

    void insert(Book book);

    void remove (Book book);
}
