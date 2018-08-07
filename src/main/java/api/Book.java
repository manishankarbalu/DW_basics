package api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@Table(name = "books")
@NamedQueries({@NamedQuery(name = "Get_Book_By_AuthID", query = "select b from Book b where b.authId = :authId "),
        @NamedQuery(name = "Get_Book_GT_Rating", query = "select b from Book b where b.rating >= :rating")
})
public class Book {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;


    @Id
    @Column(name = "bookId", nullable = false)
    @NotNull
    @JsonProperty
    private String bookId;

    @Column(name = "bookName", nullable = false)
    @NotNull
    @JsonProperty
    private String bookName;

    @JoinColumn(name = "authId", nullable = false)
    @NotNull
    @JsonProperty
    private String authId;

//    @NotNull
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "authId")
//    @JsonProperty
//    private Author author;

    @Column(name = "rating", nullable = false)
    @NotNull
    @JsonProperty
    private Integer rating;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public Book(){


    }

    public Book(String bookId, String bookName, String authId, Integer rating) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authId = authId;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getBookId(), book.getBookId()) &&
                Objects.equals(getBookName(), book.getBookName()) &&
                Objects.equals(getAuthId(), book.getAuthId()) &&
                Objects.equals(getRating(), book.getRating());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getBookId(), getBookName(), getAuthId(), getRating());
    }
}
