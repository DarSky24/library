package springframework.library.service;

import springframework.library.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre getById(int id);

    List<Genre> getAll();

    int count();

    void insert(String genreName);

    void remove (int genreId);
}
