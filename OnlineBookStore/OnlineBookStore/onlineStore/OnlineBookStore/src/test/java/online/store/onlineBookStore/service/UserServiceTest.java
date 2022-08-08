package online.store.onlineBookStore.service;

import online.store.onlineBookStore.enums.GenderEnum;
import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.entities.dtos.UserRegisterDTO;
import online.store.onlineBookStore.repositories.RoleRepository;
import online.store.onlineBookStore.repositories.UserRepository;
import online.store.onlineBookStore.services.RoleService;
import online.store.onlineBookStore.services.UserService;
import online.store.onlineBookStore.viewModel.UserViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;


import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private Role testUserRole;
    private User testUser;
    private UserService testUserService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private RoleService testRoleService;


    @Mock
    private UserRepository testUserRepository;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    private void setUp() {
        testUser = new User();
        testUserRole = new Role();
        modelMapper = new ModelMapper();
        testUserRole.setName(RoleEnum.USER);
        testUserService = new UserService(testUserRepository,modelMapper,passwordEncoder,testRoleService);
        testUser.setUsername("UserT")
                .setFirstName("Pesho")
                .setLastName("Peshov")
                .setAge(13)
                .setEmail("peshov@gmail.com")
                .setPassword("aaaaa")
                .setGender(GenderEnum.MALE)
                .setPhoneNumber("+35111111111")
                .setRole(testUserRole);
    }

    @Test
    public void notFound(){
        assertThrows(Exception.class,() -> testUserService.findByUsername("invalid"));
    };

    @Test
    public void found(){
        when(testUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));
        User byUsername = testUserService.findByUsername(testUser.getUsername());

        Assertions.assertEquals(byUsername.getUsername(),testUser.getUsername());
    }

    @Test
    public void allUser(){
        when(testUserRepository.findAll()).thenReturn(List.of(testUser));
        List<UserViewModel> all = testUserService.findAll()
                .stream().map(user -> modelMapper.map(user, UserViewModel.class)).toList();

        Assertions.assertEquals(all.size(),1);
    }

    @Test
    public void saveUser(){
        User adminUser = new User();
        testUserRole = new Role();
        modelMapper = new ModelMapper();
        passwordEncoder = new Pbkdf2PasswordEncoder();
        testUserRole.setName(RoleEnum.ADMIN);

        adminUser.setUsername("testAdminUser")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setAge(23)
                .setEmail("admin@gmail.com")
                .setPassword("22222")
                .setGender(GenderEnum.MALE)
                .setPhoneNumber("+359222222222")
                .setRole(testUserRole);

        testUserService = new UserService(testUserRepository,modelMapper,passwordEncoder,testRoleService);

        when(testUserRepository.saveAndFlush(adminUser)).thenReturn(adminUser);

        when(testUserRepository.findByUsername(adminUser.getUsername()))
                .thenReturn(Optional.of(adminUser));

        User current = this.testUserService.saveUser(adminUser);

        Assertions.assertEquals(current,testUserService.findByUsername(adminUser.getUsername()));
    }

    @Test
    public void userExistsInDataBase(){
        modelMapper = new ModelMapper();
        passwordEncoder = new Pbkdf2PasswordEncoder();
        testUserService = new UserService(testUserRepository,modelMapper,passwordEncoder,testRoleService);

        testUserService.saveUser(testUser);
        when(testUserRepository
                .findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));

        when(testUserRepository
                .findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername(testUser.getUsername()).setEmail(testUser.getEmail());

        Assertions.assertFalse(testUserService.userExistsInDatabase(userRegisterDTO));
    }
}
