package springframework.library.dao.JDBCImpl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import springframework.library.dao.GenreDAO;
import springframework.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class GenreDAOImpl implements GenreDAO {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDAOImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Genre getById(int id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select * from genres where id = :id", params, new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("name", name);
        return jdbc.queryForObject("select * from genres where genre_name = :name", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }

    @Override
    public int count() {
        final HashMap<String, Object> params = new HashMap<>(1);
        return jdbc.queryForObject("select count(*) from genres", params, Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("genreName", genre.getGenreName());
        jdbc.update("insert into genres (genreName) values (:genreName)", params);
    }

    @Override
    public void remove(Genre genre) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", genre.getId());
        jdbc.update("delete from genres where id = :id", params);
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String genreName = resultSet.getString("genre_name");
            return new Genre(id, genreName);
        }
    }
}
