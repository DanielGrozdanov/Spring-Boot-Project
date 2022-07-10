package online.store.onlineBookStore.models.services;

import online.store.onlineBookStore.models.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}
