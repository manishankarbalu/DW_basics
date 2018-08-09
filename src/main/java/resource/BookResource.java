package resource;

import api.Author;
import api.Book;
import api.CreateBookRequest;
import dao.AuthorDAO;
import dao.BookDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource  {

    BookDAO bookDAO;
    AuthorDAO authorDAO;

    public BookResource (BookDAO bookDAO,AuthorDAO authorDAO){
        this.bookDAO=bookDAO;this.authorDAO=authorDAO;
    }

    @GET
    @Path("/all/")
    @UnitOfWork
    public List<Book> getAll(){
        return bookDAO.getAll();
    }

    @POST
    @UnitOfWork
    public Book add(@Valid CreateBookRequest req){
        Author author = authorDAO.findById(req.getAuthId());
        Book book = new Book(null, req.getBookId(), req.getBookName(), author, req.getRating());
        Book newBook = bookDAO.insert(book);
        return newBook;
    }

    @GET
    @UnitOfWork public List<Book> getBooksByAuthId(@QueryParam("authId") Integer id){
        Author author = authorDAO.findById(id);
        return bookDAO.getBookByAuthId(author);
    }

    @GET
    @Path("/{rating}")
    @UnitOfWork
    public List<Book> getBookGtRating(@PathParam("rating") int rating){
        return bookDAO.getBookGtRating(rating);
    }
}
