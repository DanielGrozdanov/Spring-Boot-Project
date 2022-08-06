package online.store.onlineBookStore.web;

import online.store.onlineBookStore.models.entities.*;
import online.store.onlineBookStore.models.entities.dtos.DeliveryDTO;
import online.store.onlineBookStore.repositories.DeliveryRepository;
import online.store.onlineBookStore.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private DeliveryDTO testDeliveryDTO = new DeliveryDTO();
    private Book testBook;
    private CartBooks testCartBooks;
    private Cart testCart;
    private User testUser;


    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp(){
        testUser = testDataUtils.createTestUser("TestUser");
        testDeliveryDTO = testDataUtils.createTestDeliveryDTO();
        testBook = testDataUtils.createBook(testDataUtils.createTestCategory(),testDataUtils.createTestAuthor());
        testCart = testDataUtils.createTestCart(testUser);
        testCartBooks = testDataUtils.createTestCartBooks(testBook,testCart);
    }

    @WithMockUser(username = "TestUser",roles = "USER")
    @Test
    public void getDeliveryTest() throws Exception {
        mockMvc.perform(get("/deliveries/delivery-information"))
                .andExpect(status().isOk())
                .andExpect(view().name("delivery-information"));
        if (testCartBooks == null){
             mockMvc.perform((RequestBuilder) redirectedUrl("/books/view/all"));
        }
    }

    @WithMockUser(username = "TestUser",roles = "USER")
    @Test
    public void deliveryAddTest() throws Exception {
        mockMvc.perform(post("/deliveries/delivery-information")
                .param("person", testDeliveryDTO.getPerson())
                .param("country", testDeliveryDTO.getCountry())
                .param("phone", testDeliveryDTO.getPhone())
                .param("email", testDeliveryDTO.getEmail())
                .param("city",testDeliveryDTO.getCity())
                .param("address", testDeliveryDTO.getAddress())
                .param("postalCode", testDeliveryDTO.getPostalCode())
                .param("courier", testDeliveryDTO.getCourier())
                .with(csrf())
        ).andExpect(status().is(302));
       assertEquals(1,deliveryRepository.count());
    }

    @WithMockUser(username = "TestUser",roles = "USER")
    @Test
    public void deliveryAddTestFail() throws Exception {
        mockMvc.perform(post("/deliveries/delivery-information")
                .param("person","")
                .param("country", testDeliveryDTO.getCountry())
                .param("phone", testDeliveryDTO.getPhone())
                .param("email", testDeliveryDTO.getEmail())
                .param("city",testDeliveryDTO.getCity())
                .param("address", testDeliveryDTO.getAddress())
                .param("postalCode", testDeliveryDTO.getPostalCode())
                .param("courier", testDeliveryDTO.getCourier())
                .with(csrf())
        ).andExpect(status().is(302));
        assertEquals(0,deliveryRepository.count());
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanDataBase();
    }
}
