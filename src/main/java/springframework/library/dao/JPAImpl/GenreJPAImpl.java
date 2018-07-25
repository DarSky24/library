package springframework.library.dao.JPAImpl;

import org.springframework.stereotype.Repository;
import springframework.library.dao.GenreDAO;
import springframework.library.domain.Genre;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
@Transactional
public class GenreJPAImpl implements GenreDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public Genre getByName(String name) {
        try {

            TypedQuery<Genre> query = em.createQuery("select g from Genre g where " +
                    "g.genreName = :name", Genre.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Long count() {
        Query query = em.createQuery("select count(g) from Genre g");
        return (Long) query.getSingleResult();
    }

    @Override
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public void remove(Genre genre) {
        em.remove(genre);
    }
}
