package springframework.library.service;

import springframework.library.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre getById(Long id);

    List<Genre> getAll();

    Long count();

    void insert(String genreName);

    void remove (Long genreId);
}
