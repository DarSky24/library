package springframework.library.service.impl;

import org.springframework.stereotype.Service;
import springframework.library.dao.GenreDAO;
import springframework.library.domain.Genre;
import springframework.library.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDAO dao;

    public GenreServiceImpl(GenreDAO dao) {
        this.dao = dao;
    }

    @Override
    public Genre getById(Long id) throws IllegalArgumentException {
        Genre genre = dao.getById(id);
        if (genre == null) {
            throw new IllegalArgumentException("Не существует жанра с ID = " + id);
        }
        return genre;
    }

    @Override
    public List<Genre> getAll() {
        return dao.getAll();
    }

    @Override
    public Long count() {
        return dao.count();
    }

    @Override
    public void insert(String genreName) {
        Genre genre = dao.getByName(genreName);
        if (genre != null) {
            System.out.println("Такой жанр уже существует в базе данных, ID = " + genre.getId());
        } else {
            genre = new Genre(genreName);
            dao.insert(genre);
        }
    }

    @Override
    public void remove(Long genreId) throws IllegalArgumentException {
        Genre genre = getById(genreId);
        dao.remove(genre);
    }
}
