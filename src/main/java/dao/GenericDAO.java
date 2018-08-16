package dao;

import api.Author;
import api.Book;


import javax.persistence.TypedQuery;
import java.util.List;

public interface GenericDAO<T> {

     List<T> getAll();
     T findById(Integer id);
     void delete(T t);
     T insert(T t);
     List<T> getBookByAuthId(T t);
     List<T> getBookGtRating(int rating);

}
