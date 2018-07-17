package springframework.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import springframework.library.domain.Genre;
import springframework.library.service.GenreService;

import java.util.List;

@ShellComponent
public class GenreCommands {

    private final GenreService service;

    @Autowired
    public GenreCommands(GenreService service) {
        this.service = service;
    }

    @ShellMethod("getGenre")
    public Genre getGenre(
            @ShellOption int id
    ) {
        return service.getById(id);
    }

    @ShellMethod("getAllGenres")
    public List<Genre> getAllGenres() {
        return service.getAll();
    }

    @ShellMethod("getNumberOfGenres")
    public int getNumberOfGeners() {
        return service.count();
    }

    @ShellMethod("addGenre")
    public void insertGenre(
            @ShellOption String genreName
    ) {
        service.insert(genreName);
    }

    @ShellMethod("deleteGenre")
    public void deleteGenre(
            @ShellOption int id
    ) {
        service.remove(id);
    }

}
