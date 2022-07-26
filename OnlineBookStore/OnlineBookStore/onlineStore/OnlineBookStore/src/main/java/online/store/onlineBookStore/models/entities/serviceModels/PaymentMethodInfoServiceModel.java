package online.store.onlineBookStore.models.entities.serviceModels;


import online.store.onlineBookStore.models.entities.Order;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

public class PaymentMethodInfoServiceModel {

    private Long id;
    private String paymentType;
    private String cardNumber;
    private String expiryMonth;
    private Integer expiryYear;
    private String owner;
    private String cvc;
    private List<Order> order;

    public PaymentMethodInfoServiceModel() {

    }

    public Long getId() {
        return id;
    }

    public PaymentMethodInfoServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public PaymentMethodInfoServiceModel setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public PaymentMethodInfoServiceModel setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
        return this;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public PaymentMethodInfoServiceModel setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public PaymentMethodInfoServiceModel setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public PaymentMethodInfoServiceModel setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getCvc() {
        return cvc;
    }

    public PaymentMethodInfoServiceModel setCvc(String cvc) {
        this.cvc = cvc;
        return this;
    }

    public List<Order> getOrder() {
        return order;
    }

    public PaymentMethodInfoServiceModel setOrder(List<Order> order) {
        this.order = order;
        return this;
    }
}
