package resource;

import api.hai;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/hai")
@Produces(MediaType.APPLICATION_JSON)
public class hai_res {

    private String greeting;
    private String name ;
    public hai_res(String greeting,String name){
        this.greeting = greeting;
        this.name = name;
    }
    @GET
    @Timed
    public hai greeting(@QueryParam("name") Optional<String> name1){
        final String value = String.format(greeting,name1.or(name));
        return new hai(1,value);
    }

}
