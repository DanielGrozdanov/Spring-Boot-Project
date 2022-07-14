package online.store.onlineBookStore.models.services;

import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.enums.CategoryEnum;
import online.store.onlineBookStore.models.repositories.BookRepository;
import online.store.onlineBookStore.models.viewModel.BookViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }


    public List<BookViewModel> findAll() {
        return this.bookRepository
                .findAll()
                .stream()
                .map(book -> this.modelMapper
                        .map(book, BookViewModel.class))
                .collect(Collectors.toList());
    }

    public List<BookViewModel> findByCategoryName(CategoryEnum name) {
       return bookRepository.findByCategoryName(name)
               .stream().map(book -> this.modelMapper.map(book,BookViewModel.class))
               .collect(Collectors.toList());
    }

    public Book findBookById(Long id) {
        return this.bookRepository.findById(id).get();
    }
}
