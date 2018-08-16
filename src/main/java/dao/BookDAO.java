package dao;

import api.Author;
import api.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;


@SuppressWarnings("deprecation")
public class BookDAO extends AbstractDAO<Book>{  //implements GenericDAO<Book>
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    @Inject
    public BookDAO(@NotNull SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Book> getAll(){
        return (List<Book>) currentSession().createCriteria(Book.class).list();
    }


    public Book insert(Book book){
        return persist(book);
    }

    public List<Book> getBookByAuthId(Author author){
        TypedQuery<Book> query = currentSession().getNamedQuery("Get_Book_By_AuthID");
        query.setParameter("author",author);
        return (List<Book>) query.getResultList();
    }

    public List<Book> getBookGtRating(int rating){
        TypedQuery<Book> query = currentSession().getNamedQuery("Get_Book_GT_Rating");
        query.setParameter("rating",rating);
        return (List<Book>) query.getResultList();
    }

}
