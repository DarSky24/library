package springframework.library.domain;


import javax.persistence.*;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String genreName;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
