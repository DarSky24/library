package springframework.library.service;

import springframework.library.domain.Book;

import java.util.List;

public interface BookService {

    Book getById(Long id);

    List<Book> getByTitle(String title);

    List<Book> getAll();

    Long count();

    void insert(String title, Long genreId, Long authorId);

    void remove (Long bookId);
}
