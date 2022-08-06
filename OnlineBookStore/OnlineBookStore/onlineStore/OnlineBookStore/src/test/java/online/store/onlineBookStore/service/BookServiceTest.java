package online.store.onlineBookStore.service;

import online.store.onlineBookStore.enums.CategoryEnum;
import online.store.onlineBookStore.models.entities.Author;
import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.entities.Category;
import online.store.onlineBookStore.models.entities.dtos.AuthorDTO;
import online.store.onlineBookStore.models.entities.dtos.BookDTO;
import online.store.onlineBookStore.repositories.AuthorRepository;
import online.store.onlineBookStore.repositories.BookRepository;
import online.store.onlineBookStore.services.AuthorService;
import online.store.onlineBookStore.services.BookService;
import online.store.onlineBookStore.services.CategoryService;
import online.store.onlineBookStore.viewModel.BookViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static online.store.onlineBookStore.enums.CoverTypeEnum.Hard;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private CategoryService categoryService;

    private BookService bookService;
    private Book book;
    private Category category;
    private Author author;
    private ModelMapper modelMapper;


    @BeforeEach
    void setUp() {
        authorService = new AuthorService(authorRepository);
        book = new Book();
        category = new Category();
        author = new Author();
        modelMapper = new ModelMapper();
        category.setName(CategoryEnum.Fantasy);
        author.setAuthorName("NewAuthor");
        bookService =
                new BookService(bookRepository, modelMapper, categoryService, authorService);
        book.setTitle("NewBook");
        book.setReleaseDate(LocalDate.of(1950, 5, 12));
        book.setPublisher("ThePublisher");
        book.setPictureUrl("SomeURL");
        book.setCategory(category);
        book.setCoverType(Hard);
        book.setPrice(BigDecimal.TEN);
        book.setPages("533");
        book.setIsbn("090804940");
        book.setStock(12);
        book.setAuthor(author);
    }

    @Test
    public void SaveToDB() {
        BookDTO bookDTO = new BookDTO();
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName(author.getAuthorName());
        Author map = this.modelMapper.map(authorDTO, Author.class);

        bookDTO.setTitle("NewBook");
        bookDTO.setReleaseDate(LocalDate.of(1950, 5, 12));
        bookDTO.setPublisher("ThePublisher");
        bookDTO.setPictureUrl("SomeURL");
        bookDTO.setCategory(CategoryEnum.Fantasy);
        bookDTO.setCoverType(Hard);
        bookDTO.setPrice(BigDecimal.TEN);
        bookDTO.setPages("533");
        bookDTO.setIsbn("090804940");
        bookDTO.setStock(12);
        bookDTO.setAuthor(authorDTO);



        authorService.saveAuthor(map);
        bookService.saveToDB(bookDTO);

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(bookArgumentCaptor.capture());

    }

    @Test
    public void findBookById(){
        when(bookRepository
                .findById(book.getId())).thenReturn(Optional.of(book));

        Book book1 = bookService.findBookById(book.getId());


        Assertions.assertEquals(book1.getTitle(),book.getTitle());
    }


    @Test
    public void deleteBookById(){
        bookRepository.saveAndFlush(book);
        bookRepository.deleteById(book.getId());
        bookService.deleteById(book.getId());

        List<BookViewModel> bookViewModels = bookService.findAll().stream()
                .map(b -> modelMapper.map(b, BookViewModel.class)).toList();

        Assertions.assertEquals(bookViewModels.size(),0);
    }

    @Test
    public void bookExistsInDB(){

        BookDTO bookDTO = new BookDTO();
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName(author.getAuthorName());

        bookDTO.setTitle("NewBook");
        bookDTO.setReleaseDate(LocalDate.of(1950, 5, 12));
        bookDTO.setPublisher("ThePublisher");
        bookDTO.setPictureUrl("SomeURL");
        bookDTO.setCategory(CategoryEnum.Fantasy);
        bookDTO.setCoverType(Hard);
        bookDTO.setPrice(BigDecimal.TEN);
        bookDTO.setPages("533");
        bookDTO.setIsbn("090804940");
        bookDTO.setStock(12);
        bookDTO.setAuthor(authorDTO);

        bookService.saveToDB(bookDTO);

        bookService.findByBookTitle(bookDTO.getTitle());
        bookService.findByBookIsbn(bookDTO.getIsbn());
        verify(bookRepository).findByTitle(bookDTO.getTitle());
        verify(bookRepository).findBookByIsbn(bookDTO.getIsbn());
    }

}
