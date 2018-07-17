package springframework.library.dao;

import springframework.library.domain.Author;
import springframework.library.domain.Genre;

import java.util.List;

public interface GenreDAO {

    Genre getById(int id);

    Genre getByName(String name);

    List<Genre> getAll();

    int count();

    void insert(Genre genre);

    void remove (Genre genre);
}
