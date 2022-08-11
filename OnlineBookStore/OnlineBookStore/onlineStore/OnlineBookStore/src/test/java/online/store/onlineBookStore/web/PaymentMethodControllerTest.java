package online.store.onlineBookStore.web;


import online.store.onlineBookStore.models.entities.*;
import online.store.onlineBookStore.repositories.PaymentMethodRepository;
import online.store.onlineBookStore.utilities.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PaymentMethodControllerTest {


    @Autowired
    private TestDataUtils testDataUtils;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;



    private User testUser;
    private PaymentMethod testPaymentMethod;
    private Delivery testDelivery;



    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setUp() {

        testUser = testDataUtils.createTestUser("TestUser");
        testPaymentMethod = testDataUtils
                .createTestPaymentMethod(testDataUtils.createTestPaymentMethodTO(),testUser);
        testDelivery = testDataUtils.createTestDelivery(testDataUtils.createTestDeliveryDTO(),testUser);
    }



    @Test
    void testOrderCompletedPage() throws Exception {
        mockMvc.perform(get("/payments/order-completed"))
                .andExpect(status().is(302));
    }

    @WithMockUser(username = "TestUser", roles = "USER")
    @Test
    public void testGetPaymentMethod() throws Exception {
        mockMvc.perform(get("/payments/payment-information")).andExpect(status().is(200));
    }


    @WithMockUser(username = "TestUser", roles = "USER")
    @Test
    public void testPostPaymentMethod() throws Exception {
        mockMvc.perform(post("/payments//payment-information")
                .param("cardNumber", testPaymentMethod.getCardNumber())
                .param("owner", testPaymentMethod.getOwner())
                .param("cvc", testPaymentMethod.getCvc())
                .param("expiryMonth", testPaymentMethod.getExpiryMonth())
                .param("expiryYear", testPaymentMethod.getExpiryYear().toString())
                .with(csrf())
        ).andExpect(status().is(302));
        assertEquals(2, paymentMethodRepository.count());
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanDataBase();
    }
}
