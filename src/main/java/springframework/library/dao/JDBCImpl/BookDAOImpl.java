package springframework.library.dao.JDBCImpl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import springframework.library.dao.AuthorDAO;
import springframework.library.dao.BookDAO;
import springframework.library.dao.GenreDAO;
import springframework.library.domain.Author;
import springframework.library.domain.Book;
import springframework.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorDAO authorDAO;
    private final GenreDAO genreDAO;

    public BookDAOImpl(NamedParameterJdbcOperations jdbc, AuthorDAO authorDAO, GenreDAO genreDAO) {
        this.jdbc = jdbc;
        this.authorDAO = authorDAO;
        this.genreDAO = genreDAO;
    }

    @Override
    public Book getById(int id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        try {
            return jdbc.queryForObject("select * from books where id = :id", params, new BookMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getByTitle(String title) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("title", title);
        return jdbc.query("select * from books where title = :title", params, new BookMapper());
    }

    @Override
    public Book getByParams(String title, int genreId, int authorId) {
        final HashMap<String, Object> params = new HashMap<>(3);
        params.put("title", title);
        params.put("genreId", genreId);
        params.put("authorId", authorId);
        return jdbc.queryForObject("select * from books where title = :title and genre_id = :genreId and author_id = :authorId", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @Override
    public int count() {
        final HashMap<String, Object> params = new HashMap<>(1);
        return jdbc.queryForObject("select count(*) from books", params, Integer.class);
    }

    @Override
    public void insert(Book book) {
        final HashMap<String, Object> params = new HashMap<>(3);
        params.put("title", book.getTitle());
        params.put("genre_id", book.getGenre().getId());
        params.put("author_id", book.getAuthor().getId());
        jdbc.update("insert into books (title, genre_id, author_id) values (:title, :genre_id, :author_id)", params);
    }

    @Override
    public void remove(Book book) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", book.getId());
        jdbc.update("delete from books where id = :id", params);
    }

    private class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            Author author = authorDAO.getById(resultSet.getInt("author_id"));
            Genre genre = genreDAO.getById(resultSet.getInt("genre_id"));
            return new Book(id, title, genre, author);
        }
    }
}
