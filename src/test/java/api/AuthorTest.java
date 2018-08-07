package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AuthorTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Author expectedAuth = new Author("101", "vaibhav");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue("{\"authId\":\"101\",\"authName\":\"vaibhav\"}", Author.class));

        assertThat(MAPPER.writeValueAsString(expectedAuth)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Author person = new Author("102", "archan");
        assertThat(MAPPER.readValue("{\"authId\":\"102\",\"authName\":\"archan\"}", Author.class))
                .isEqualTo(person);
    }
}
