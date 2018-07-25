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

    @ShellMethod("Получить жанр по ID")
    public Genre getGenre(
            @ShellOption Long id
    ) {
        return service.getById(id);
    }

    @ShellMethod("Получить список всех жанров")
    public List<Genre> getAllGenres() {
        return service.getAll();
    }

    @ShellMethod("Получить количество жанров")
    public Long getNumberOfGeners() {
        return service.count();
    }

    @ShellMethod("Добавить новый жанр, необходимый параметр: genreName")
    public void addGenre(
            @ShellOption String genreName
    ) {
        service.insert(genreName);
    }

    @ShellMethod("Удалить жанр по ID")
    public void deleteGenre(
            @ShellOption Long id
    ) {
        service.remove(id);
    }

}
