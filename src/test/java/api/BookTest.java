package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BookTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private final Author author = new Author(null,1,"Shankar");
    final Book expectedBook = new Book(null,21,"Testing Book",author,1);
    @Test
    public void serializesToJSON() throws Exception {

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue("{\"bookId\":21,\"bookName\":\"Testing Book\",\"author\":{\"authId\":1,\"authName\":\"Shankar\"},\"rating\":1}", Book.class));

        assertThat(MAPPER.writeValueAsString(expectedBook)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {

        assertThat(MAPPER.readValue("{\"bookId\":21,\"bookName\":\"Testing Book\",\"author\":{\"authId\":1,\"authName\":\"Shankar\"},\"rating\":1}", Book.class))
                .isEqualTo(expectedBook);
    }

}
