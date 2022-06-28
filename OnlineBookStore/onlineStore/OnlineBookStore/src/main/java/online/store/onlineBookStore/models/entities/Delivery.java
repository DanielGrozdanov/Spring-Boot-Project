package online.store.onlineBookStore.models.entities;


import org.aspectj.weaver.ast.Or;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "delivery")
public class Delivery extends BaseEntity{

    @Column(nullable = false)
    private String deliveryContractor;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private String phone;

    private String email;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String postalCode;

    @OneToMany
    private List<Order> orders;

    public Delivery() {

    }

    public String getDeliveryContractor() {
        return deliveryContractor;
    }

    public Delivery setDeliveryContractor(String deliveryContractor) {
        this.deliveryContractor = deliveryContractor;
        return this;
    }

    public String getUser() {
        return user;
    }

    public Delivery setUser(String user) {
        this.user = user;
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

    public String getCountry() {
        return country;
    }

    public Delivery setCountry(String country) {
        this.country = country;
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

    public List<Order> getOrders() {
        return orders;
    }

    public Delivery setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
