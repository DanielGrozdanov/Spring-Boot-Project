package online.store.onlineBookStore.models.services;

import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.entities.dtos.UserRegisterDTO;
import online.store.onlineBookStore.models.enums.GenderEnum;
import online.store.onlineBookStore.models.enums.RoleEnum;
import online.store.onlineBookStore.models.repositories.UserRepository;
import online.store.onlineBookStore.models.viewModel.UserRegServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public void addFirstUser() {
        if (this.userRepository.count() == 0) {
            User firstUser = new User();
            Role adminRole = roleService.findByName(RoleEnum.ADMIN);
            firstUser.setUsername("FinalBoss");
            firstUser.setFirstName("Final");
            firstUser.setLastName("Boss");
            firstUser.setAge(42);
            firstUser.setEmail("final.boss@gmail.com");
            firstUser.setPassword(passwordEncoder.encode("bigBoss"));
            firstUser.setGender(GenderEnum.MALE);
            firstUser.setPhoneNumber("+359 456 654");
            firstUser.setRole(adminRole);
            this.userRepository.save(firstUser);

            User secondUser = new User();
            Role userRole = roleService.findByName(RoleEnum.USER);
            secondUser.setUsername("TheNewbie");
            secondUser.setFirstName("The");
            secondUser.setLastName("Newbie");
            secondUser.setAge(24);
            secondUser.setEmail("the.newbie@gmail.com");
            secondUser.setPassword(passwordEncoder.encode("newBieUser"));
            secondUser.setGender(GenderEnum.MALE);
            secondUser.setPhoneNumber("+359 344 443");
            secondUser.setRole(userRole);

            this.userRepository.save(secondUser);
        }
    }

    public UserRegServiceModel registerUserMap(UserRegisterDTO userRegisterDTO) {
        UserRegServiceModel userReg = modelMapper.map(userRegisterDTO,UserRegServiceModel.class);
        Role userRole = this.roleService.findByName(RoleEnum.USER);

        userReg.setRole(userRole);
        userReg.setOrders(new ArrayList<>());
        userReg.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        return userReg;
    }

    public boolean userExistsInDatabase(UserRegisterDTO userRegisterDTO){
        Optional<User> checkIfUserExistsByEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());
        Optional<User> checkIfUserExistsByUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());
        return checkIfUserExistsByEmail.isEmpty() && checkIfUserExistsByUsername.isEmpty();
    }


    public User register(UserRegServiceModel userRegisterModel) {
        User user = this.modelMapper.map(userRegisterModel,User.class);
        user.setRole(this.roleService.findByName(RoleEnum.USER));
        return this.userRepository.save(user);
    }
}