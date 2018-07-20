package springframework.library.dao.JDBCImpl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import springframework.library.dao.AuthorDAO;
import springframework.library.domain.Author;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private final NamedParameterJdbcOperations jdbc;

    private RowMapper<Author> rowMapper = (rs, rowNum) -> {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        return new Author(id, name, surname);
    };

    public AuthorDAOImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Author getById(int id) {
        final Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return jdbc.queryForObject("select * from authors where id = :id", params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        final Map<String, Object> params = new HashMap<>(2);
        params.put("name", name);
        params.put("surname", surname);
        try {
            return jdbc.queryForObject("select * from authors where name = :name and surname = :surname", params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", rowMapper);
    }

    @Override
    public int count() {
        final Map<String, Object> params = Collections.emptyMap();
        return jdbc.queryForObject("select count(*) from authors", params, Integer.class);
    }

    @Override
    public void insert(Author author) {
        final Map<String, Object> params = new HashMap<>(2);
        params.put("name", author.getName());
        params.put("surname", author.getSurname());
        jdbc.update("insert into authors (name, surname) values (:name, :surname)", params);
    }

    @Override
    public void remove(Author author) {
        final Map<String, Object> params = Collections.singletonMap("id", author.getId());
        jdbc.update("delete from authors where id = :id", params);

    }

}
