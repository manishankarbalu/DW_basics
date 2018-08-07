package ResourceTest;


import api.Author;
import dao.AuthorDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import resource.AuthorResource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class AuthorResourceTest {

    private static final AuthorDAO dao = mock(AuthorDAO.class);

    @ClassRule
    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AuthorResource(dao))
            .build();

    private final Author author = new Author("001","Shankar");

    @Before
    public void setup() {

        when(dao.findById(eq("001"))).thenReturn(author);
    }

    @Test
    public void getAuthorTest() {
        System.out.println(resources);System.out.println(dao);
        Author responseAuthor = resources.target("/author/001").request().get(Author.class);
        assertThat(responseAuthor)
                .isEqualTo(author);
        System.out.println("\nExpected : "+ author.toString());
        System.out.println("Actual : "+ responseAuthor.toString());
        verify(dao).findById("001");
    }

}
