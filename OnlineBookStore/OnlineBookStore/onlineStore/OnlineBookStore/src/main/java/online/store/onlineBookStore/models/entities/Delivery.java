package online.store.onlineBookStore.models.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery")
public class Delivery extends BaseEntity {

    @Column(nullable = false)
    private String person;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false,name = "postal_code")
    private String postalCode;

    @Column(nullable = false)
    private String courier;


    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany
    private List<Order> orders;

    public Delivery() {

    }

    public List<Order> getOrders() {
        return orders;
    }


    public Delivery setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public String getPerson() {
        return person;
    }

    public Delivery setPerson(String person) {
        this.person = person;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Delivery setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Delivery setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Delivery setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Delivery setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Delivery setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Delivery setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCourier() {
        return courier;
    }

    public User getUser() {
        return user;
    }

    public Delivery setUser(User user) {
        this.user = user;
        return this;
    }

    public Delivery setCourier(String courier) {
        this.courier = courier;
        return this;
    }
}