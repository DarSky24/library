package springframework.library.dao.JPAImpl;

import org.springframework.stereotype.Repository;
import springframework.library.dao.AuthorDAO;
import springframework.library.domain.Author;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
@Transactional
public class AuthorJPAImpl implements AuthorDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author getById(Long id) {
        return em.find(Author.class, id);
    }

    @Override
    public Author getByNameAndSurname(String name, String surname) {
        try {
            TypedQuery<Author> query = em.createQuery("select a from Author a where " +
                    "a.name = :name and a.surname = :surname", Author.class);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Long count() {
        Query query = em.createQuery("select count(a) from Author a");
        return (Long) query.getSingleResult();
    }

    @Override
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public void remove(Author author) {
        em.remove(author);
    }
}
