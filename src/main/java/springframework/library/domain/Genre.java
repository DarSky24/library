package springframework.library.domain;


public class Genre {

    private int id;
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    @Override
    public String toString() {
        return id + ": " + genreName;
    }
}
