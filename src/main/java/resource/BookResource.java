package resource;

import api.Author;
import api.Book;
import api.CreateBookRequest;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import service.AuthorService;
import service.BookService;


import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource  {

    //BookDAO bookDAO;
    //AuthorDAO authorDAO;
    private final AuthorService authorService;
    private final BookService bookService;

    @Inject
    public BookResource(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @GET
    @Path("/all/")
    @UnitOfWork
    public List<Book> getAll(){
        return bookService.getAll();
    }

    @POST
    @UnitOfWork
    public Book add(@Valid CreateBookRequest req){
        Author author = authorService.findById(req.getAuthId());
        Book book = new Book(null, req.getBookId(), req.getBookName(), author, req.getRating());
        Book newBook = bookService.insert(book);
        return newBook;
    }

    @GET
    @UnitOfWork public List<Book> getBooksByAuthId(@QueryParam("authId") Integer id){
        Author author = authorService.findById(id);
        return bookService.getBookByAuthId(author);
    }

    @GET
    @Path("/{rating}")
    @UnitOfWork
    public List<Book> getBookGtRating(@PathParam("rating") int rating){
        return bookService.getBookGtRating(rating);
    }
}
