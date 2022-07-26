package online.store.onlineBookStore.models.entities.serviceModels;

import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.models.enums.GenderEnum;

import java.util.List;

public class UserRegServiceModel {


    private String username;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private String password;

    private GenderEnum gender;

    private String phoneNumber;

    private Role role;

    private List<Cart> carts;

    public UserRegServiceModel() {

    }

    public String getUsername() {
        return username;
    }

    public UserRegServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserRegServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserRegServiceModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserRegServiceModel setRole(Role role) {
        this.role = role;
        return this;
    }

    public List<Cart> getOrders() {
        return carts;
    }

    public UserRegServiceModel setOrders(List<Cart> carts) {
        this.carts = carts;
        return this;
    }
}
