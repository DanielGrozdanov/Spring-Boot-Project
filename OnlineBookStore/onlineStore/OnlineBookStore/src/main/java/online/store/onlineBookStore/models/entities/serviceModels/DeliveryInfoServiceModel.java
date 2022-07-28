package online.store.onlineBookStore.models.entities.serviceModels;

import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.entities.Order;

import java.util.List;

public class DeliveryInfoServiceModel {

    private Long id;
    private String person;
    private String country;
    private String phone;
    private String email;
    private String city;
    private String address;
    private String postalCode;
    private String courier;
    private List<Order> orders;


    public DeliveryInfoServiceModel() {

    }

    public List<Order> getOrders() {
        return orders;
    }

    public DeliveryInfoServiceModel setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DeliveryInfoServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerson() {
        return person;
    }

    public DeliveryInfoServiceModel setPerson(String person) {
        this.person = person;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public DeliveryInfoServiceModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public DeliveryInfoServiceModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DeliveryInfoServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCity() {
        return city;
    }

    public DeliveryInfoServiceModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryInfoServiceModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public DeliveryInfoServiceModel setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCourier() {
        return courier;
    }

    public DeliveryInfoServiceModel setCourier(String courier) {
        this.courier = courier;
        return this;
    }
}
