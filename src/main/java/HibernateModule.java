import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dao.AuthorDAO;
import dao.BookDAO;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;


public class HibernateModule extends AbstractModule {

    private final HibernateBundle<AppConfiguration> hibernateBundle;

    public HibernateModule(HibernateBundle<AppConfiguration> hibernateBundle)
    {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {


    }

//    @Provides
//    public AuthorDAO providesAuthorDAO(){
//        return new AuthorDAO(hibernateBundle.getSessionFactory());
//    }
//
//    @Provides
//    public BookDAO providesBookDAO(){
//        return new BookDAO(hibernateBundle.getSessionFactory());
//    }
//
    @Provides
    @Singleton
    public SessionFactory getSessionFactory(){
        return hibernateBundle.getSessionFactory();
    }
}
