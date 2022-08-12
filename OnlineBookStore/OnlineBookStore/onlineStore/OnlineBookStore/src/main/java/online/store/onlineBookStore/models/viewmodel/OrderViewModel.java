package online.store.onlineBookStore.models.viewmodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderViewModel {

    private Long id;
    private String client;
    private String payment;
    private String delivery;
    private BigDecimal totalPrice;

    public OrderViewModel() {

    }

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getClient() {
        return client;
    }

    public OrderViewModel setClient(String client) {
        this.client = client;
        return this;
    }

    public String getPayment() {
        return payment;
    }

    public OrderViewModel setPayment(String payment) {
        this.payment = payment;
        return this;
    }

    public String getDelivery() {
        return delivery;
    }

    public OrderViewModel setDelivery(String delivery) {
        this.delivery = delivery;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderViewModel setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}

