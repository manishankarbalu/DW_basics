package api;

import io.dropwizard.jersey.errors.ErrorMessage;
import org.hibernate.exception.ConstraintViolationException;


import javax.persistence.PersistenceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class DuplicateExceptionMapper implements ExceptionMapper<PersistenceException> {

    public Response toResponse(final PersistenceException exception) {
        Response response;
        if (exception.getCause() instanceof ConstraintViolationException) {

            response =
                    Response.status(CONFLICT).type(MediaType.APPLICATION_JSON_TYPE)
                            .entity(new Author(null,0,"Exception :"+((ConstraintViolationException) exception.getCause()).getSQLException().getMessage()))
                                   .build();
//            new ErrorMessage(
//                    CONFLICT.getStatusCode(), "Constraint violation failure", ((ConstraintViolationException) exception.getCause()).getSQLException().getMessage()))

        } else {
            response =
                    Response.status(INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON_TYPE)
                            .entity(
                                    new ErrorMessage(INTERNAL_SERVER_ERROR.getStatusCode(), "Persistence failure"))
                            .build();
        }

        return response;
    }
}
