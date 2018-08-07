package resource;

import api.Book;
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

    public BookResource (BookDAO bookDAO){
        this.bookDAO=bookDAO;
    }

    @GET
    @Path("/all/")
    @UnitOfWork
    public List<Book> getAll(){
        return bookDAO.getAll();
    }

    @POST
    @UnitOfWork
    public Book add(@Valid Book book){
        Book newBook = bookDAO.insert(book);
        return newBook;
    }

    @GET
    @UnitOfWork public List<Book> getBooksByAuthId(@QueryParam("authId") String id){
        return bookDAO.getBookByAuthId(id);
    }

    @GET
    @Path("/{rating}")
    @UnitOfWork
    public List<Book> getBookGtRating(@PathParam("rating") int rating){
        return bookDAO.getBookGtRating(rating);
    }
}
