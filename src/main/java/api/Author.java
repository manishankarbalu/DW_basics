package api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "authId", nullable = false,unique = true)
    @NotNull
    @JsonProperty
    private Integer authId;

    @NotNull
    @Column(name = "authName", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonProperty
    private String authName;

}
