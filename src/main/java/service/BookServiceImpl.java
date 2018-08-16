package service;

import api.Author;
import api.Book;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import dao.BookDAO;
import dao.GenericDAO;


import java.util.List;

//@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Inject
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public Book insert(Book book) {
        return bookDAO.insert(book);
    }

    @Override
    public List<Book> getBookByAuthId(Author author) {
        return bookDAO.getBookByAuthId(author);
    }

    @Override
    public List<Book> getBookGtRating(int rating) {
        return bookDAO.getBookGtRating(rating);
    }
}
