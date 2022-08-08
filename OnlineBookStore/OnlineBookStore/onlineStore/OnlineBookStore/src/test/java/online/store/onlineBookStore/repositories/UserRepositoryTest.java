package online.store.onlineBookStore.repositories;


import online.store.onlineBookStore.enums.GenderEnum;
import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.models.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        Role role = new Role();
        role.setName(RoleEnum.USER);
        roleRepository.save(role);
        Role foundRole = roleRepository.findByName(role.getName());

        User user = new User()
                .setUsername("UserT")
                .setFirstName("Pesho")
                .setLastName("Peshov")
                .setAge(13)
                .setEmail("peshov@gmail.com")
                .setPassword("aaaaa")
                .setGender(GenderEnum.MALE)
                .setPhoneNumber("+35111111111")
                .setRole(foundRole);
        this.userRepository.saveAndFlush(user);
    }

    @AfterEach
    void purge(){
        roleRepository.deleteAll();
        userRepository.deleteAll();
    }
}
