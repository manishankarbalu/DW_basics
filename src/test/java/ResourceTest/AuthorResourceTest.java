package ResourceTest;


import api.Author;
import com.squarespace.jersey2.guice.JerseyGuiceUtils;
import dao.AuthorDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import resource.AuthorResource;
import service.AuthorServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorResourceTest {

    //private static final AuthorDAO dao = mock(AuthorDAO.class);


    static {
        JerseyGuiceUtils.install((s, serviceLocator) -> null);
    }

    @InjectMocks
    private static AuthorServiceImpl authorServiceImpl = mock(AuthorServiceImpl.class);

    //@Mock
    //private AuthorService authorService = mock(AuthorService.class);

    @Mock
    private static AuthorDAO authorDAO = mock(AuthorDAO.class);

    @ClassRule

    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AuthorResource(authorServiceImpl))
            .build();

    private final Author author = new Author(null,1,"Shankar");


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        //when(authorServiceImpl.findById(eq(1))).thenReturn(author);
        doReturn(author).when(authorServiceImpl).findById(anyInt());
        //doReturn(author).when(authorDAO).findById(anyInt());
    }

    @Test
    public void getAuthorTest() {
        System.out.println(resources);
        Author responseAuthor = resources.target("/author/001").request().get(Author.class);
        assertThat(responseAuthor)
                .isEqualTo(author);
        System.out.println("\nExpected : "+ author.toString());
        System.out.println("Actual : "+ responseAuthor.toString());
        verify(authorServiceImpl).findById(1);
    }

}
