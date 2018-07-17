package springframework.library.service;

import springframework.library.domain.Book;

import java.util.List;

public interface BookService {

    Book getById(int id);

    List<Book> getByTitle(String title);

    List<Book> getAll();

    int count();

    void insert(String title, int genreId, int authorId);

    void remove (int bookId);
}
