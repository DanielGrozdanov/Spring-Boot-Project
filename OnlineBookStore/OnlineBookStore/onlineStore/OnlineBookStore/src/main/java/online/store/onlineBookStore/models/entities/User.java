package online.store.onlineBookStore.models.entities;


import online.store.onlineBookStore.models.enums.GenderEnum;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne
    private Role role;

    @OneToMany
    private Collection<Cart> cart;

    public User() {

    }


    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public User setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public Collection<Cart> getCart() {
        return cart;
    }

    public User setCart(Collection<Cart> cart) {
        this.cart = cart;
        return this;
    }
}
