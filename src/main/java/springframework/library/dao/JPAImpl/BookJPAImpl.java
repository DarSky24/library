package springframework.library.dao.JPAImpl;

import org.springframework.stereotype.Repository;
import springframework.library.dao.BookDAO;
import springframework.library.domain.Author;
import springframework.library.domain.Book;
import springframework.library.domain.Genre;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
@Transactional
public class BookJPAImpl implements BookDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book getById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getByTitle(String title) {
        try {
            TypedQuery<Book> query = em.createQuery("select b from Book b where " +
                    "b.title = :title", Book.class);
            query.setParameter("title", title);
            return query.getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Book getByParams(String title, Genre genre, Author author) {
        try {
            TypedQuery<Book> query = em.createQuery("select b from Book b where " +
                    "b.title = :title and :genreId member of b.genres.id " +
                    "and :authorId member of b.authors.id", Book.class);
            query.setParameter("title", title);
            query.setParameter("genre", genre);
            query.setParameter("author", author);
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Long count() {
        Query query = em.createQuery("select count(b) from Book b");
        return (Long) query.getSingleResult();
    }

    @Override
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    public void remove(Book book) {
        em.remove(book);
    }
}
