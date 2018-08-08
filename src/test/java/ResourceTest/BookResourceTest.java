package ResourceTest;

import api.Author;
import api.Book;
import dao.AuthorDAO;
import dao.BookDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import resource.BookResource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookResourceTest {
    private static final BookDAO bkdao = mock(BookDAO.class);
    private static final AuthorDAO audao = mock(AuthorDAO.class);

    @ClassRule
    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new BookResource(bkdao,audao))
            .build();

    private final Author author = new Author(null,1,"shankar");
    private final Book book1 = new Book(null,233,"Harry Potter",author,2);
    private Book book2 =new Book(null,233,"Hamlet",author,5);
    private List<Book> bookList = new ArrayList<>();
    @Before
    public void setup() {
        bookList.add(book1);
        bookList.add(book2);

        when(bkdao.getAll()).thenReturn(bookList);
        when(bkdao.getBookByAuthId(eq(author))).thenReturn(bookList);
        when(audao.findById(anyInt())).thenReturn(author);
    }

    @Test
    public void getAllBookTest() {
        String expected ="[{bookId=233, bookName=Harry Potter, author={authId=1, authName=shankar}, rating=2}, {bookId=233, bookName=Hamlet, author={authId=1, authName=shankar}, rating=5}]";
        List<Book> capturedList = resources.target("/book/all").request().get(ArrayList.class);
        System.out.println("\nExpected : "+ expected);
        System.out.println("Actual : "+ capturedList.toString());
        assertThat(capturedList.toString())
                .isEqualTo(expected);
        verify(bkdao).getAll();
    }

    @Test
    public void getBookByAuthIdTest(){
        String expected ="[{bookId=233, bookName=Harry Potter, author={authId=1, authName=shankar}, rating=2}, {bookId=233, bookName=Hamlet, author={authId=1, authName=shankar}, rating=5}]";
        List<Book> capturedList= resources.target("/book").queryParam("authId", 1).request().get(ArrayList.class);

        System.out.println("\nExpected : "+ expected);
        System.out.println("Actual : "+ capturedList.toString());
        assertThat(capturedList.toString())
                .isEqualTo(expected);
        verify(bkdao).getBookByAuthId(author);
    }

}
