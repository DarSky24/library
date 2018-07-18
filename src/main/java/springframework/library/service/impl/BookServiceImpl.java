package springframework.library.service.impl;

import org.springframework.stereotype.Service;
import springframework.library.dao.BookDAO;
import springframework.library.domain.Author;
import springframework.library.domain.Book;
import springframework.library.domain.Genre;
import springframework.library.service.AuthorService;
import springframework.library.service.BookService;
import springframework.library.service.GenreService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO dao;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookDAO dao, AuthorService authorService, GenreService genreService) {
        this.dao = dao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public Book getById(int id) throws IllegalArgumentException {
        Book book = dao.getById(id);
        if (book == null) {
            throw new IllegalArgumentException("Не существует книг с ID = " + id);
        }
        return book;
    }

    @Override
    public List<Book> getByTitle(String title) throws IllegalArgumentException {
        List<Book> books = dao.getByTitle(title);
        if (books == null || books.size() == 0) {
            throw new IllegalArgumentException("Не существует книг с названием " + title);
        }
        return books;
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public void insert(String title, int genreId, int authorId) throws IllegalArgumentException {
        Book book = dao.getByParams(title, genreId, authorId);
        if (book != null) {
            System.out.println("Такая книга уже существует в базе данных, ID = " + book.getId());
        } else {
            Author author = authorService.getById(authorId);
            Genre genre = genreService.getById(genreId);
            book = new Book(title, genre, author);
            dao.insert(book);
        }
    }

    @Override
    public void remove(int bookId) throws IllegalArgumentException {
        Book book = getById(bookId);
        dao.remove(book);
    }
}
