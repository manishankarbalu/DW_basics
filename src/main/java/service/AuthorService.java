package service;

import api.Author;


import java.util.List;

public interface AuthorService {
     List<Author> getAll();
     Author findById(Integer id);
     void delete(Author author);
     Author insert(Author author);
}
