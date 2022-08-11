package online.store.onlineBookStore.service;

import online.store.onlineBookStore.enums.CategoryEnum;
import online.store.onlineBookStore.enums.GenderEnum;
import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.*;
import online.store.onlineBookStore.repositories.AuthorRepository;
import online.store.onlineBookStore.repositories.BookRepository;
import online.store.onlineBookStore.repositories.CartBooksRepository;
import online.store.onlineBookStore.repositories.CartRepository;
import online.store.onlineBookStore.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static online.store.onlineBookStore.enums.CoverTypeEnum.Hard;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    private CartService cartService;

    @Mock
    private CartBookService cartBookService;
    private User testUser;
    private Book testBook;
    private Cart cart;
    private CartBooks cartBooks;
    private Set<CartBooks> cartBooksCollection;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private CategoryService categoryService;

    private BookService bookService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartBooksRepository cartBooksRepository;

    @BeforeEach
    private void SetUp() {
        cartService = new CartService(cartRepository,cartBooksRepository);
        testUser = new User();
        Role testRole = new Role();
        testRole.setName(RoleEnum.USER);
        testUser.setUsername("UserT")
                .setFirstName("Pesho")
                .setLastName("Peshov")
                .setAge(13)
                .setEmail("peshov@gmail.com")
                .setPassword("aaaaa")
                .setGender(GenderEnum.MALE)
                .setPhoneNumber("+35111111111")
                .setRole(testRole);

        authorService = new AuthorService(authorRepository);
        Book book = new Book();
        Category category = new Category();
        Author author = new Author();
        ModelMapper modelMapper = new ModelMapper();
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

        cartBooks = new CartBooks();
        cartBooks.setBook(book);
        cartBooks.setCart(cart);
        cartBooksCollection = new HashSet<>();
        cartBooks.setBook(book);
        cartBooksCollection.add(cartBooks);
        cart = new Cart();
        cart.setCart(cartBooksCollection);
        cart.setUser(testUser);
        cart.setStatus("open");


    }

    @Test
    public void save(){
        Cart cart = new Cart();
        cart.setCart(this.cartBooksCollection);
        cart.setUser(testUser);
        cart.setStatus("open");
        this.cartService.saveCart(cartBooks,cart);
    }

    @Test
    void purgeTable(){
        cartService.purgeCartTable();
        verify(cartRepository).deleteAll();
    }
}
