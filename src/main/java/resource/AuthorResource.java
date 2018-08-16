package resource;

import api.Author;
import com.google.inject.Inject;
import io.dropwizard.hibernate.UnitOfWork;
import service.AuthorService;



import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/author")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AuthorResource {

    //AuthorDAO authordao;

    private final AuthorService authorService;

    @Inject
    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    //private Author a =new Author(3L,1,"mani");



    @GET
    @Path("/all/")
    @UnitOfWork
    public List<Author> getAll(){
        return authorService.getAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Author get(@PathParam("id") Integer id){
        return authorService.findById(id);
    }

    @POST
    @UnitOfWork()
    public Author add(@Valid Author author) {

        Author newAuther = authorService.insert(author);
            return newAuther;
    }

//    @PUT
//    @Path("/{id}")
//    @UnitOfWork
//    public Author update(@PathParam("id") Integer id, @Valid Author author) {
//        author.setAuthId(id);
//        authordao.update(author);
//        return author;
//    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public String delete(@PathParam("id") Integer id) {
        authorService.delete(authorService.findById(id));
        return "{\"delete\" :\"successfull\"}\t";
    }
}
