package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotNull
    @JsonProperty
    private Integer bookId;

    @NotNull
    @JsonProperty
    private String bookName;

    @NotNull
    @JsonProperty
    private Integer authId;

    @NotNull
    @JsonProperty
    private Integer rating;

}
