package springframework.library.dao;

import springframework.library.domain.Author;
import springframework.library.domain.Genre;

import java.util.List;

public interface GenreDAO {

    Genre getById(Long id);

    Genre getByName(String name);

    List<Genre> getAll();

    Long count();

    void insert(Genre genre);

    void remove (Genre genre);
}
