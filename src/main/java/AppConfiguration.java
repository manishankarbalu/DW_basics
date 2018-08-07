import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AppConfiguration extends Configuration {

    @NotEmpty
    private String greeting;

    @NotEmpty
    private String name = "anonymous";

    @JsonProperty
    public String getGreeting() {
        return greeting;
    }

    @JsonProperty
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @NotNull
    public DataSourceFactory getDatabase() {
        //System.out.println(database);
        return database;
    }
}
