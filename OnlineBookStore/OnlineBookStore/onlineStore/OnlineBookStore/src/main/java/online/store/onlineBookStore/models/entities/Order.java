package online.store.onlineBookStore.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @OneToMany
    private List<CartBooks> books;

    @ManyToOne
    private PaymentMethod paymentMethod;

    @ManyToOne
    private Delivery delivery;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false,name = "total_value")
    private BigDecimal totalValue;

    @ManyToOne
    private User user;

    public Order() {

    }

    public List<CartBooks> getBooks() {
        return books;
    }

    public Order setBooks(List<CartBooks> books) {
        this.books = books;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Order setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Order setDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Order setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Order setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }
}
