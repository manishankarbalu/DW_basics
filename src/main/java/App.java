import api.Author;
import api.Book;
import api.DuplicateExceptionMapper;
import dao.BookDAO;
import dao.AuthorDAO;
import health.DatabaseHealthCheck;
import health.healthCheck;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.AuthorResource;
import resource.BookResource;
import resource.hai_res;


public class App extends Application<AppConfiguration> {


    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    /** Prepare the hibernate bundle  **/
    private final HibernateBundle<AppConfiguration> hibernate = new HibernateBundle<AppConfiguration>(Author.class,Book.class) {
        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            System.out.println(configuration.getDatabase());
            return configuration.getDatabase();
        }
    };

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws ClassNotFoundException{

        /** Add author, dao and its resource to bind with main app thread **/
        final AuthorDAO authordao = new AuthorDAO(hibernate.getSessionFactory());
        System.out.println(authordao);
        final AuthorResource authorResource = new AuthorResource(authordao);
        System.out.println(authorResource);
        environment.jersey().register(authorResource);

        /** Add book, dao and its resource to bind with main app thread **/
        final BookDAO bookDAO = new BookDAO(hibernate.getSessionFactory());
        System.out.println(bookDAO);
        final BookResource bookResource =new BookResource(bookDAO,authordao);
        System.out.println(bookResource);
        environment.jersey().register(bookResource);

        /** Bind the Custom exception with the main app thread **/
        environment.jersey().register(DuplicateExceptionMapper.class);

        /**  register database healthcheck **/
        environment.healthChecks().register("mysql", new DatabaseHealthCheck(configuration.getDatabase()));
        //final hai_res resource = new hai_res(configuration.getGreeting(),configuration.getName());
        //final healthCheck hc = new healthCheck(configuration.getGreeting());
        //environment.healthChecks().register("greeting", hc);
        //environment.jersey().register(resource);

    }

    @Override
    public String getName() {
        return "Welcome";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        /** inititalize hibernate when the app starts **/
    bootstrap.addBundle(hibernate);
    }
}
