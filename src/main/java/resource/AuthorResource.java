package resource;

import api.Author;
import dao.AuthorDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.FlushMode;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;
import java.util.List;

@Path("/author")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AuthorResource {

    AuthorDAO authordao;

    public AuthorResource(AuthorDAO authordao) {
        this.authordao = authordao;
    }

    private Author a =new Author(3L,1,"mani");
    @GET
    @Path("/all/")
    @UnitOfWork
    public List<Author> getAll(){
        return authordao.getAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Author get(@PathParam("id") Integer id){
        return authordao.findById(id);
    }

    @POST
    @UnitOfWork()
    public Author add(@Valid Author author) {

        Author newAuther = authordao.insert(author);
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
        authordao.delete(authordao.findById(id));
        return "{\"delete\" :\"successfull\"}\t";
    }
}
