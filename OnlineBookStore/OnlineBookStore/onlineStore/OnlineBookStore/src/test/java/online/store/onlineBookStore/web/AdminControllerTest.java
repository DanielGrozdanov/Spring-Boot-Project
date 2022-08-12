package online.store.onlineBookStore.web;


import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.repositories.BookRepository;
import online.store.onlineBookStore.services.BookService;
import online.store.onlineBookStore.utilities.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    private Book newBook;
    private User testAdmin;

    @BeforeEach
    void setUp() {

        testAdmin = testDataUtils.createTestAdmin("TestAdmin");
        newBook = testDataUtils.createNewBookToAdd(testDataUtils.createTestBookDTO(testDataUtils.createTestAuthorDTO()), testDataUtils.createTestCategory());
    }


    @WithMockUser(username = "TestAdmin", roles = "ADMIN")
    @Test
    public void testPostAddBookPage() throws Exception {

        mockMvc.perform(post("/admin/book-add")
                .param("title",newBook.getTitle())
                .param("releaseDate",newBook.getReleaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .param("publisher",newBook.getPublisher())
                .param("pictureUrl",newBook.getPictureUrl())
                .param("category",newBook.getCategory().getName().name())
                .param("coverType",newBook.getCoverType().name())
                .param("price", String.valueOf(newBook.getPrice().doubleValue()))
                .param("pages",newBook.getPages())
                .param("isbn",newBook.getIsbn())
                .param("stock",newBook.getStock().toString())
                .param("author.authorName",newBook.getAuthor().getAuthorName())
                .with(csrf())
        ).andExpect(status().is(302)).andExpect(redirectedUrl("/admin/book-add"));
    }

    @WithMockUser(username = "TestAdmin", roles = "ADMIN")
    @Test
    public void testGetAddBookPage() throws Exception {
        mockMvc.perform(get("/admin/book-add"))
                .andExpect(status().is(200));
    }


    @WithMockUser(username = "TestAdmin", roles = "ADMIN")
    @Test
    public void allUsersPage() throws Exception {
        mockMvc.perform(get("/admin/all-users")).andExpect(status().is(200));
    }

    @WithMockUser(username = "TestAdmin", roles = "ADMIN")
    @Test
    public void allOrders() throws Exception {
        mockMvc.perform(get("/admin/orders-list")).andExpect(status().is(200));
    }

    @WithMockUser(username = "TestAdmin", roles = "ADMIN")
    @Test
    public void searchUserPage() throws Exception {
        mockMvc.perform(get("/admin/all-users/search")).andExpect(status().is(200));
    }
    @AfterEach
    void tearDown() {
        testDataUtils.cleanDataBase();
    }
}
