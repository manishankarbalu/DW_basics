package service;

import api.Author;
import com.google.inject.Inject;
import dao.AuthorDAO;
import lombok.RequiredArgsConstructor;


import java.util.List;

@RequiredArgsConstructor(onConstructor = @_(@Inject))
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDAO authorDAO;

    @Override
    public List<Author> getAll() {
        return authorDAO.getAll();
    }

    @Override
    public Author findById(Integer id) {
        return authorDAO.findById(id);
    }

    @Override
    public void delete(Author author) {
        authorDAO.delete(author);
    }

    @Override
    public Author insert(Author author) {
        return authorDAO.insert(author);
    }
}
