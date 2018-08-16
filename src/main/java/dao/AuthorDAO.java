package dao;

import api.Author;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import javax.validation.constraints.NotNull;
import java.util.List;

@SuppressWarnings("deprecation")
public class AuthorDAO extends AbstractDAO<Author> { //implements GenericDAO<Author>


    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    @Inject
    public AuthorDAO(@NotNull SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<Author> getAll(){
        return (List<Author>) currentSession().createCriteria(Author.class).list();
    }
    public Author findById(Integer id){
       // List<Author> author1 = new ArrayList<>();
        return (Author) currentSession().createCriteria(Author.class).add(Restrictions.eq("authId",id)).uniqueResult();
        //Author author = new Author("001","shankar");
        //return author1;
    }
    public void delete(Author author){
        currentSession().delete(author);
    }
    public Author insert(Author author){

        return persist(author);

    }
//    public void update(Author author){
//        currentSession().saveOrUpdate(author);
//    }
}
