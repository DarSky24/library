package springframework.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import springframework.library.domain.Book;
import springframework.library.domain.Genre;
import springframework.library.service.BookService;
import springframework.library.service.GenreService;

import java.util.List;

@ShellComponent
public class BookCommands {

    private final BookService service;

    @Autowired
    public BookCommands(BookService service) {
        this.service = service;
    }

    @ShellMethod("getBook")
    public Book getBook(
            @ShellOption int id
    ) {
        return service.getById(id);
    }

    @ShellMethod("getAllBooks")
    public List<Book> getAllBooks() {
        return service.getAll();
    }

    @ShellMethod("getNumberOfBooks")
    public int getNumberOfBooks() {
        return service.count();
    }

    @ShellMethod("addBook")
    public void addBook(
            @ShellOption String title,
            @ShellOption int genreId,
            @ShellOption int authorId
    ) {
        service.insert(title, genreId, authorId);
    }

    @ShellMethod("deleteBook")
    public void deleteBook(
            @ShellOption int id
    ) {
        service.remove(id);
    }

}
