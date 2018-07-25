package springframework.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import springframework.library.domain.Author;
import springframework.library.service.AuthorService;

import java.util.List;

@ShellComponent
public class AuthorCommands {

    private final AuthorService service;

    @Autowired
    public AuthorCommands(AuthorService service) {
        this.service = service;
    }

    @ShellMethod("Получить автора по ID")
    public Author getAuthor(
            @ShellOption Long id
    ) {
        return service.getById(id);
    }

    @ShellMethod("Получить список всех авторов")
    public List<Author> getAllAuthors() {
        return service.getAll();
    }

    @ShellMethod("Получить количество авторов")
    public Long getNumberOfAuthors(){
        return service.count();
    }

    @ShellMethod("Добавить нового автора, необходимые параметры: name, surname")
    public void addAuthor(
            @ShellOption String name,
            @ShellOption String surname
    ){
        service.insert(name, surname);
    }

    @ShellMethod("Удалить автора по ID")
    public void deleteAuthor(
            @ShellOption Long id
    ) {
        service.remove(id);
    }

}
