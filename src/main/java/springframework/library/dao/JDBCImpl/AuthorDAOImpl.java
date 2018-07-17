package springframework.library.dao.JDBCImpl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import springframework.library.dao.AuthorDAO;
import springframework.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDAOImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Author getById(int id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        try {
            return jdbc.queryForObject("select * from authors where id = :id", params, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        final HashMap<String, Object> params = new HashMap<>(2);
        params.put("name", name);
        params.put("surname", surname);
        try {
            return jdbc.queryForObject("select * from authors where name = :name and surname = :surname", params, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public int count() {
        final HashMap<String, Object> params = new HashMap<>(1);
        return jdbc.queryForObject("select count(*) from authors", params, Integer.class);
    }

    @Override
    public void insert(Author author) {
        final HashMap<String, Object> params = new HashMap<>(2);
        params.put("name", author.getName());
        params.put("surname", author.getSurname());
        jdbc.update("insert into authors (name, surname) values (:name, :surname)", params);
    }

    @Override
    public void remove(Author author) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", author.getId());
        jdbc.update("delete from authors where id = :id", params);

    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            return new Author(id, name, surname);
        }
    }
}
