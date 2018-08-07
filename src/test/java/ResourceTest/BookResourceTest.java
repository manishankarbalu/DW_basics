package ResourceTest;

import api.Book;
import dao.BookDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import resource.BookResource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookResourceTest {
    private static final BookDAO dao = mock(BookDAO.class);

    @ClassRule
    public static ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new BookResource(dao))
            .build();

    private final Book book1 = new Book("b233","Harry Potter","004",4);
    private Book book2 =new Book("bk234","Tom and Jerry","005",2);
    private List<Book> bookList = new ArrayList<>();
    @Before
    public void setup() {
        bookList.add(book1);bookList.add(book2);
        when(dao.getAll()).thenReturn(bookList);
    }

    @Test
    public void getAllBookTest() {
        String expected ="[{bookId=b233, bookName=Harry Potter, authId=004, rating=4}, {bookId=bk234, bookName=Tom and Jerry, authId=005, rating=2}]";
        List<Book> capturedList = resources.target("/book/all").request().get(ArrayList.class);
        System.out.println("\nExpected : "+ expected);
        System.out.println("Actual : "+ capturedList.toString());
        assertThat(capturedList.toString())
                .isEqualTo(expected);
        verify(dao).getAll();
    }

}
