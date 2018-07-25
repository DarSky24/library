package springframework.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import springframework.library.domain.Book;
import springframework.library.service.BookService;

import java.util.List;

@ShellComponent
public class BookCommands {

    private final BookService service;

    @Autowired
    public BookCommands(BookService service) {
        this.service = service;
    }

    @ShellMethod("Получить книгу по ID")
    public Book getBook(
            @ShellOption Long id
    ) {
        return service.getById(id);
    }

    @ShellMethod("Получить список книг по названию")
    public List<Book> getBookByTitle(
            @ShellOption String title
    ) {
        return service.getByTitle(title);
    }

    @ShellMethod("Получить список всех книг")
    public List<Book> getAllBooks() {
        return service.getAll();
    }

    @ShellMethod("Получить количество книг")
    public Long getNumberOfBooks() {
        return service.count();
    }

    @ShellMethod("Добавить книгу, необходимые параметры: title, genreId, authorId")
    public void addBook(
            @ShellOption String title,
            @ShellOption Long genreId,
            @ShellOption Long authorId
    ) {
        service.insert(title, genreId, authorId);
    }

    @ShellMethod("Удалить книгу по ID")
    public void deleteBook(
            @ShellOption Long id
    ) {
        service.remove(id);
    }

}
