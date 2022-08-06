
package online.store.onlineBookStore.service;

import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.enums.GenderEnum;
import online.store.onlineBookStore.repositories.UserRepository;
import online.store.onlineBookStore.services.OnlineBookStoreDetailsService;
import online.store.onlineBookStore.user.OnlineBookStoreUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class OnlineBookStoreUserDetailsServiceTest {

    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;
    private OnlineBookStoreDetailsService onlineBookStoreDetailsService;


    @BeforeEach
    void setUp() {
        User user = new User();
        Role role1 = new Role();
        modelMapper = new ModelMapper();
        Role role = role1.setName(RoleEnum.USER);
        user.setUsername("TestUser")
                .setFirstName("Test")
                .setLastName("User")
                .setAge(26)
                .setEmail("userTestov@gmail.com")
                .setPassword("123")
                .setRole(role)
                .setGender(GenderEnum.MALE)
                .setPhoneNumber("+359 222 222 222");
        onlineBookStoreDetailsService = new OnlineBookStoreDetailsService(userRepository);

    }

    @Test
    public void mapOnlineBookStoreDetailsError() {
        assertThrows(
                Exception.class, () -> onlineBookStoreDetailsService.loadUserByUsername("something")
        );
    }

    @Test
    public void userRoleMapTest() {
        OnlineBookStoreDetailsService newUser = new OnlineBookStoreDetailsService(userRepository);
        User user = new User();
        Role role = new Role();
        role.setName(RoleEnum.USER);
        user.setRole(role);
        user.setPassword("1234");
        user.setEmail("newUser@gmail.com");
        UserDetails userDetails = newUser.userRoleMap(user);
        assertEquals(userDetails.getAuthorities().toArray()[0].toString(),"ROLE_USER");
    }

    @Test
    public void adminRoleMapTest() {
        OnlineBookStoreDetailsService newUser = new OnlineBookStoreDetailsService(userRepository);
        User user = new User();
        Role role = new Role();
        role.setName(RoleEnum.ADMIN);
        user.setRole(role);
        user.setPassword("1234");
        user.setEmail("adminUser@gmail.com");
        UserDetails userDetails = newUser.adminRoleMap(user);
        assertEquals(userDetails.getAuthorities().toArray()[0].toString(),"ROLE_ADMIN");
    }
}

