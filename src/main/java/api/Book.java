package api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "books")
@NamedQueries({@NamedQuery(name = "Get_Book_GT_Rating", query = "select b from Book b where b.rating >= :rating"),
        @NamedQuery(name = "Get_Book_By_AuthID", query = "select b from Book b where b.author = :author "),
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({ "id", "bookId", "bookName", "author", "rating" })
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    @JsonIgnore
    private Long id;


    @Column(name = "bookId", nullable = false, unique = true)
    @NotNull
    @JsonProperty
    private Integer bookId;

    @Column(name = "bookName", nullable = false)
    @NotNull
    @JsonProperty
    private String bookName;

//    @JoinColumn(name = "authId", nullable = false)
//    @NotNull
//    @JsonProperty
//    private String authId;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authId")
    @JsonProperty
    private Author author;

    @Column(name = "rating", nullable = true)
    @NotNull
    @JsonProperty
    private Integer rating;

}
