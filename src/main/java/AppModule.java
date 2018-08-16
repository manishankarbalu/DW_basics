import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import resource.AuthorResource;
import resource.BookResource;
import service.AuthorService;
import service.AuthorServiceImpl;
import service.BookService;
import service.BookServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(BookService.class).to(BookServiceImpl.class);
        bind(AuthorService.class).to(AuthorServiceImpl.class);
        bind(BookResource.class).in(Singleton.class);
        bind(AuthorResource.class).in(Singleton.class);
    }
}
