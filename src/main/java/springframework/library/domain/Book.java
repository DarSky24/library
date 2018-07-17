package springframework.library.domain;


public class Book {

    private int id;
    private String title;
    private Genre genre;
    private Author author;

    public Book(String title, Genre genre, Author author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public Book(int id, String title, Genre genre, Author author) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return id + ": Книга " + "\"" + title + "\". " + author.toString() + ". " + genre.toString() + ".";
    }


}
