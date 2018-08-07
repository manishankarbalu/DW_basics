package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BookTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Book expectedBook = new Book("b201","book201","004",4);

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue("{\"bookId\":\"b201\",\"bookName\":\"book201\",\"authId\":\"004\",\"rating\":4}", Book.class));

        assertThat(MAPPER.writeValueAsString(expectedBook)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Book book = new Book("b201","book201","004",4);
        assertThat(MAPPER.readValue("{\"bookId\":\"b201\",\"bookName\":\"book201\",\"authId\":\"004\",\"rating\":4}", Book.class))
                .isEqualTo(book);
    }

}
