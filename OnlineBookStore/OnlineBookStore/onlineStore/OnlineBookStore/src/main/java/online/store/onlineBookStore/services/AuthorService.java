package online.store.onlineBookStore.services;

import online.store.onlineBookStore.models.entities.Author;
import online.store.onlineBookStore.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

   private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void saveAuthor(Author authorName) {
        this.authorRepository.save(authorName);
    }
}
