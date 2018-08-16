package service;

import api.Author;
import api.Book;

import java.util.List;

public interface BookService {

     List<Book> getAll();
     Book insert(Book book);
     List<Book> getBookByAuthId(Author author);
     List<Book> getBookGtRating(int rating);
}
