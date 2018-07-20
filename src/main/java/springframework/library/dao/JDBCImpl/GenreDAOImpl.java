package springframework.library.dao.JDBCImpl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import springframework.library.dao.GenreDAO;
import springframework.library.domain.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDAOImpl implements GenreDAO {

    private final NamedParameterJdbcOperations jdbc;

    private RowMapper<Genre> rowMapper = (rs, rowNum) -> {
        int id = rs.getInt("id");
        String genreName = rs.getString("genre_name");
        return new Genre(id, genreName);
    };

    public GenreDAOImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Genre getById(int id) {
        final Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return jdbc.queryForObject("select * from genres where id = :id", params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Genre getByName(String name) {
        final Map<String, Object> params = Collections.singletonMap("name", name);
        try {
            return jdbc.queryForObject("select * from genres where genre_name = :name", params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", rowMapper);
    }

    @Override
    public int count() {
        final Map<String, Object> params = Collections.emptyMap();
        return jdbc.queryForObject("select count(*) from genres", params, Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        final Map<String, Object> params = Collections.singletonMap("genreName", genre.getGenreName());
        jdbc.update("insert into genres (genreName) values (:genreName)", params);
    }

    @Override
    public void remove(Genre genre) {
        final Map<String, Object> params = Collections.singletonMap("id", genre.getId());
        jdbc.update("delete from genres where id = :id", params);
    }

}
