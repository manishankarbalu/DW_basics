package api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "author")
public class Author {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
//    @JoinColumn(name = "authId", nullable = false)
    @NotNull
    @JsonProperty
    private String authId;

    @NotNull
    @Column(name = "authName", nullable = false)
    @JsonProperty
    private String authName;

    public Author(){

    }

    public Author(String authId, String authName) {
        this.authId = authId;
        this.authName = authName;
    }

    public String getAuthId() {
        return authId;
    }

    public Author setAuthId(String authId) {
        this.authId = authId;
        return this;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(authId, author.authId) &&
                Objects.equals(authName, author.authName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(authId, authName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId='" + authId + '\'' +
                ", authorName='" + authName + '\'' +
                '}';
    }
}
