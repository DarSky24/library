package springframework.library.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    @ManyToMany
    private List<Genre> genres;
    @ManyToMany
    private List<Author> authors;

    public Book() {
    }

    public Book(String title, List<Genre> genres, List<Author> authors) {
        this.title = title;
        this.genres = genres;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
