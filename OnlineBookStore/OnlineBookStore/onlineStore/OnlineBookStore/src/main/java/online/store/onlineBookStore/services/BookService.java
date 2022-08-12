package online.store.onlineBookStore.services;

import online.store.onlineBookStore.models.entities.Author;
import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.entities.dtos.BookDTO;
import online.store.onlineBookStore.enums.CategoryEnum;
import online.store.onlineBookStore.repositories.BookRepository;
import online.store.onlineBookStore.models.viewmodel.BookViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper, CategoryService categoryService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.authorService = authorService;
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
                .stream().map(book -> this.modelMapper.map(book, BookViewModel.class))
                .collect(Collectors.toList());
    }

    public Book findBookById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(Error::new);
    }

    public boolean findByBookTitle(String title) {
        return this.bookRepository.findByTitle(title).isPresent();
    }

    public boolean findByBookIsbn(String isbn) {
        return this.bookRepository.findBookByIsbn(isbn).isPresent();
    }

    public void saveToDB(BookDTO bookDTO) {
        Book book = this.modelMapper.map(bookDTO, Book.class);
        book.setCategory(categoryService.getCategory(bookDTO.getCategory()));

        Author author = this.modelMapper.map(bookDTO.getAuthor(), Author.class);
        book.setAuthor(author);

        this.authorService.saveAuthor(author);
        this.bookRepository.save(book);
    }


    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }


    public BookViewModel findById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(Error::new);
        return this.modelMapper.map(book, BookViewModel.class);
    }

    public void saveEditsToDB(BookViewModel bookViewModel, Long id) {
        Book book = this.modelMapper.map(bookViewModel, Book.class);
        this.bookRepository.updateBook(book.getPrice(), book.getStock(), id);
    }
}
