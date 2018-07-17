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

    @ShellMethod("getAuthor")
    public Author getAuthor(
            @ShellOption int id
    ) {
        return service.getById(id);
    }

    @ShellMethod("getAllAuthors")
    public List<Author> getAllAuthors() {
        return service.getAll();
    }

    @ShellMethod("getNumberOfAuthors")
    public int getNumberOfAuthors(){
        return service.count();
    }

    @ShellMethod("addAuthor")
    public void addAuthor(
            @ShellOption String name,
            @ShellOption String surname
    ){
        service.insert(name, surname);
    }

    @ShellMethod("deleteAuthor")
    public void deleteAuthor(
            @ShellOption int id
    ) {
        service.remove(id);
    }

}
