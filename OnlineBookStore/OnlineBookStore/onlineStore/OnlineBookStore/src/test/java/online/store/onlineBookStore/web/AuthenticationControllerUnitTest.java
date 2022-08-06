package online.store.onlineBookStore.web;

import online.store.onlineBookStore.repositories.UserRepository;
import online.store.onlineBookStore.util.TestDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerUnitTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void prep() {
       userRepository.deleteAll();
    }


    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testGetLoginPage() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().is(200))
                .andExpect(view().name("login"));
    }
    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "TestUser")
                        .param("firstName", "Test")
                        .param("lastName", "User")
                        .param("age", "23")
                        .param("email", "testUser@gmail.com")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("phoneNumber", "+359 942 345 234")
                        .param("gender", "MALE")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
        assertEquals(1, userRepository.count());

    }

    @Test
    public void testUserRegistrationFail() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("username", "TestUser")
                .param("firstName", "Test")
                .param("lastName", "User")
                .param("age", "23")
                .param("email", "testUser@gmail.com")
                .param("password", "test123")
                .param("confirmPassword", "")
                .param("phoneNumber", "+359 942 345 234")
                .param("gender", "MALE")
                .with(csrf())

        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/register"));
        assertEquals(0, userRepository.count());
    }



    @WithMockUser(
            username = "TestUser",
            roles = "USER"
    )
    @Test
    void userProfileCard() throws Exception {
        mockMvc.perform(get("/users/profile"))
                .andExpect(status().is(302));
    }

    @Test
    void testLoginPageShow() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

}
