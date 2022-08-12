package online.store.onlineBookStore.models.viewmodel;

import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.enums.GenderEnum;

public class UserViewModel {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private GenderEnum gender;

    private String phoneNumber;

    private Role role;

    public UserViewModel() {

    }

    public UserViewModel(String username, String firstName, String lastName, Integer age, String email, GenderEnum gender, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserViewModel setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserViewModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserViewModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
