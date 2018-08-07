package dao;

import api.Author;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.validation.constraints.NotNull;
import java.util.List;

@SuppressWarnings("deprecation")
public class AuthorDAO extends AbstractDAO<Author> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public AuthorDAO(@NotNull SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<Author> getAll(){
        return (List<Author>) currentSession().createCriteria(Author.class).list();
    }
    public Author findById(String id){
        return (Author) currentSession().get(Author.class,id);
        //Author author = new Author("001","shankar");
        //return author;
    }
    public void delete(Author author){
        currentSession().delete(author);
    }
    public Author insert(Author author){
        return persist(author);
    }
    public void update(Author author){
        currentSession().saveOrUpdate(author);
    }
}
