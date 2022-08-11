package online.store.onlineBookStore.utilities;

import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.*;
import online.store.onlineBookStore.models.entities.dtos.AuthorDTO;
import online.store.onlineBookStore.models.entities.dtos.BookDTO;
import online.store.onlineBookStore.models.entities.dtos.DeliveryDTO;
import online.store.onlineBookStore.models.entities.dtos.PaymentMethodDTO;
import online.store.onlineBookStore.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

import static online.store.onlineBookStore.enums.CategoryEnum.Fantasy;
import static online.store.onlineBookStore.enums.CoverTypeEnum.Hard;
import static online.store.onlineBookStore.enums.GenderEnum.MALE;

@Component
public class TestDataUtils {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CategoryRepository categoryRepository;
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private CartBooksRepository cartBooksRepository;
    private CartRepository cartRepository;
    private DeliveryRepository deliveryRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private ModelMapper modelMapper;

    public TestDataUtils(UserRepository userRepository, RoleRepository roleRepository,
                         CategoryRepository categoryRepository,
                         AuthorRepository authorRepository, BookRepository bookRepository,
                         CartBooksRepository cartBooksRepository, CartRepository cartRepository, DeliveryRepository deliveryRepository, PaymentMethodRepository paymentMethodRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.cartBooksRepository = cartBooksRepository;
        this.cartRepository = cartRepository;
        this.deliveryRepository = deliveryRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.modelMapper = modelMapper;
    }

    private void initRoles() {
        if (userRepository.count() == 0) {
            Role adminRole = new Role().setName(RoleEnum.ADMIN);
            Role userRole = new Role().setName(RoleEnum.USER);

            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }
    }

    public Cart createTestCart(User user) {
        var cart = new Cart()
                .setUser(user)
                .setStatus("open");
        return this.cartRepository.save(cart);
    }


    public CartBooks createTestCartBooks(Book book, Cart cart) {
        var cartBook = new CartBooks()
                .setBook(book)
                .setCart(cart)
                .setAmount(10);
        return this.cartBooksRepository.save(cartBook);
    }

    public User createTestAdmin(String username) {
        initRoles();
        var admin = new User()
                .setUsername(username)
                .setFirstName("Admino")
                .setLastName("Adminov")
                .setEmail("adminov@gmail.com")
                .setPassword("asd")
                .setAge(25)
                .setGender(MALE)
                .setPhoneNumber("+359 882 324 425")
                .setRole(roleRepository.findByName(RoleEnum.ADMIN));
        return userRepository.save(admin);
    }

    public User createTestUser(String username) {
        var user = new User()
                .setUsername(username)
                .setFirstName("User")
                .setLastName("Userov")
                .setEmail("user@gmail.com")
                .setPassword("asd")
                .setAge(23)
                .setGender(MALE)
                .setPhoneNumber("+359 222 333 444")
                .setRole(roleRepository.findByName(RoleEnum.USER));
        return userRepository.save(user);
    }

    public Book createBook(Category category, Author author) {
        var book = new Book()
                .setTitle("SomeTitle")
                .setReleaseDate(LocalDate.of(1996, 3, 4))
                .setPublisher("thePublisher")
                .setPictureUrl("https://www.sulitest.org/files/source/logo%20test%20horiz%20bis.png?1574185700651")
                .setCategory(category)
                .setCoverType(Hard)
                .setPrice(BigDecimal.TEN)
                .setPages("234")
                .setIsbn("0098385992")
                .setStock(10)
                .setAuthor(author);

        return bookRepository.save(book);
    }


    public Category createTestCategory() {
        var category = new Category()
                .setName(Fantasy)
                .setDescription("someDescription");

        return categoryRepository.save(category);
    }

    public Author createTestAuthor() {
        var author = new Author()
                .setAuthorName("The Test Author");

        return authorRepository.save(author);
    }

    public PaymentMethodDTO createTestPaymentMethodTO() {
        return new PaymentMethodDTO()
                .setCardNumber("2222 3333 4444 5555")
                .setOwner("TestOwner")
                .setCvc("124")
                .setExpiryMonth("January")
                .setExpiryYear(2027);
    }

    public BookDTO createTestBookDTO(AuthorDTO authorDTO) {
        return new BookDTO()
                .setTitle("NewBook")
                .setReleaseDate(LocalDate.of(2000, 6, 13))
                .setPublisher("NewPublisher")
                .setPictureUrl("https://www.at-languagesolutions.com/en/wp-content/uploads/2016/06/http-1.jpg")
                .setCategory(Fantasy)
                .setCoverType(Hard)
                .setPrice(BigDecimal.valueOf(29.99))
                .setPages("542")
                .setIsbn("99392481")
                .setStock(15)
                .setAuthor(authorDTO);
    }

    public Book createNewBookToAdd(BookDTO testBookDTO, Category category) {
        AuthorDTO author = testBookDTO.getAuthor();
        Author mappedAuthor = this.modelMapper.map(author, Author.class);
        this.authorRepository.save(mappedAuthor);

        var newBook = new Book()
                .setTitle(testBookDTO.getTitle())
                .setReleaseDate(testBookDTO.getReleaseDate())
                .setPublisher(testBookDTO.getPublisher())
                .setPictureUrl(testBookDTO.getPictureUrl())
                .setCategory(category.setName(testBookDTO.getCategory()))
                .setCoverType(testBookDTO.getCoverType())
                .setPrice(testBookDTO.getPrice())
                .setPages(testBookDTO.getPages())
                .setIsbn(testBookDTO.getIsbn())
                .setStock(testBookDTO.getStock())
                .setAuthor(mappedAuthor);


       return this.bookRepository.save(newBook);
    }

    public AuthorDTO createTestAuthorDTO() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("TheAddedAuthor");
        return authorDTO;
    }


    public DeliveryDTO createTestDeliveryDTO() {
        return new DeliveryDTO()
                .setPerson("SomePerson")
                .setCountry("SomeCountry")
                .setPhone("+359453325642")
                .setEmail("testEmail@gmail.com")
                .setCity("TestCity")
                .setAddress("TestAddress")
                .setPostalCode("2351")
                .setCourier("TestCourier");
    }

    public PaymentMethod createTestPaymentMethod(PaymentMethodDTO testPaymentMethodTO, User testUser) {
        var paymentMethod = new PaymentMethod()
                .setCardNumber(testPaymentMethodTO.getCardNumber())
                .setExpiryMonth(testPaymentMethodTO.getExpiryMonth())
                .setExpiryYear(testPaymentMethodTO.getExpiryYear())
                .setOwner(testPaymentMethodTO.getOwner())
                .setCvc(testPaymentMethodTO.getCvc())
                .setUser(testUser);
        return this.paymentMethodRepository.save(paymentMethod);
    }

    public Delivery createTestDelivery(DeliveryDTO deliveryDTO, User user) {
        var delivery = new Delivery()
                .setPerson(deliveryDTO.getPerson())
                .setCountry(deliveryDTO.getCountry())
                .setPhone(deliveryDTO.getPhone())
                .setEmail(deliveryDTO.getEmail())
                .setCity(deliveryDTO.getCity())
                .setAddress(deliveryDTO.getAddress())
                .setPostalCode(deliveryDTO.getPostalCode())
                .setCourier(deliveryDTO.getCourier())
                .setUser(user);
        return this.deliveryRepository.save(delivery);

    }


    public void cleanDataBase() {

        paymentMethodRepository.deleteAll();
        deliveryRepository.deleteAll();
        cartBooksRepository.deleteAll();
        cartRepository.deleteAll();
        bookRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
        authorRepository.deleteAll();
        categoryRepository.deleteAll();
    }

}
