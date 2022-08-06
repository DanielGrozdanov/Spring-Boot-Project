package online.store.onlineBookStore.web;

import online.store.onlineBookStore.models.entities.*;
import online.store.onlineBookStore.repositories.RoleRepository;
import online.store.onlineBookStore.repositories.UserRepository;
import online.store.onlineBookStore.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    private User testUser;
    private Book book;
    private Cart cart;
    private CartBooks cartBooks;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {
        testDataUtils.cleanDataBase();

        testUser = testDataUtils.createTestUser("TestUser");
        book = testDataUtils.createBook(testDataUtils.createTestCategory(), testDataUtils.createTestAuthor());
        cart = testDataUtils.createTestCart(testUser);
        cartBooks = testDataUtils.createTestCartBooks(book, cart);
        Set<CartBooks> set = new HashSet<>();
        set.add(cartBooks);
        cart.setCart(set);

    }
    @AfterEach
    void tearDown() {
        testDataUtils.cleanDataBase();
    }



    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    public void getIndex() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().is(200))
                .andExpect(view().name("index"));
    }
    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    public void addBookToCartTest() throws Exception {
        mockMvc.perform(get("/books/cart/" + book.getId()))
                .andExpect(status().is(302));
    }

    @WithMockUser(
            username = "Test",
            roles = "USER"
    )
    @Test
    public void removeBookFromCartTest() throws Exception {
        mockMvc.perform(get("/books/cart/remove/" + book.getId())).andExpect(status().is(302));
    }

    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    public void getCartTest() throws Exception {
        mockMvc.perform(get("/books/cart")).andExpect(status().is(200));
    }


    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    public void viewListOfAllBooksTest() throws Exception {
        mockMvc.perform(get("/books/view/all")).andExpect(status().isOk());
    }


    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    public void viewBooksByCategoryFantasy() throws Exception {
        mockMvc.perform(get("/books/category/fantasy")).andExpect(status().isOk());
    }

    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    public void viewBooksByCategoryHorror() throws Exception {
        mockMvc.perform(get("/books/category/horror")).andExpect(status().isOk());
    }

    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    public void viewBooksByCategoryPsychology() throws Exception {
        mockMvc.perform(get("/books/category/psychology")).andExpect(status().isOk());
    }

    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    public void viewBooksByCategoryRomance() throws Exception {
        mockMvc.perform(get("/books/category/romance")).andExpect(status().isOk());
    }

}