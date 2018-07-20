package springframework.library.dao.JDBCImpl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import springframework.library.dao.BookDAO;
import springframework.library.domain.Author;
import springframework.library.domain.Book;
import springframework.library.domain.Genre;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {

    private final NamedParameterJdbcOperations jdbc;

    private RowMapper<Book> rowMapper = (rs, rowNum) -> {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        int authorId = rs.getInt("author_id");
        String authorName = rs.getString("name");
        String authorSurname = rs.getString("surname");
        int genreId = rs.getInt("genre_id");
        String genreName = rs.getString("genre_name");
        Author author = new Author(authorId, authorName, authorSurname);
        Genre genre = new Genre(genreId, genreName);
        return new Book(id, title, genre, author);
    };

    public BookDAOImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Book getById(int id) {
        final Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return jdbc.queryForObject("select * from books b " +
                    "join authors a on b.author_id = a.id " +
                    "join genres g on b.genre_id = g.id where b.id = :id", params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getByTitle(String title) {
        final Map<String, Object> params = Collections.singletonMap("title", title);
        try {
            return jdbc.query("select * from books b " +
                    "join authors a on b.author_id = a.id " +
                    "join genres g on b.genre_id = g.id where b.title = :title", params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Book getByParams(String title, int genreId, int authorId) {
        final Map<String, Object> params = new HashMap<>(3);
        params.put("title", title);
        params.put("genreId", genreId);
        params.put("authorId", authorId);
        try {
            return jdbc.queryForObject("select * from books b " +
                    "join authors a on b.author_id = a.id " +
                    "join genres g on b.genre_id = g.id " +
                    "where b.title = :title and b.genre_id = :genreId and b.author_id = :authorId", params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books b " +
                "join authors a on b.author_id = a.id " +
                "join genres g on b.genre_id = g.id", rowMapper);
    }

    @Override
    public int count() {
        final Map<String, Object> params = Collections.emptyMap();
        return jdbc.queryForObject("select count(*) from books", params, Integer.class);
    }

    @Override
    public void insert(Book book) {
        final Map<String, Object> params = new HashMap<>(3);
        params.put("title", book.getTitle());
        params.put("genre_id", book.getGenre().getId());
        params.put("author_id", book.getAuthor().getId());
        jdbc.update("insert into books (title, genre_id, author_id) values (:title, :genre_id, :author_id)", params);
    }

    @Override
    public void remove(Book book) {
        final Map<String, Object> params = Collections.singletonMap("id", book.getId());
        jdbc.update("delete from books where id = :id", params);
    }

}
