package online.store.onlineBookStore.models.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    private User user;

    @Column(nullable = false,name = "order_price")
    private BigDecimal orderPrice;

    @OneToMany
    private List<Book> book;

    @Column(nullable = false,name = "order_date")
    private LocalDate orderDate;

    @ManyToOne
    private PaymentMethod payment;

    @ManyToOne
    private Delivery delivery;

    public Order() {

    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public Order setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public List<Book> getBook() {
        return book;
    }

    public Order setBook(List<Book> book) {
        this.book = book;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public Order setPayment(PaymentMethod payment) {
        this.payment = payment;
        return this;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Order setDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }
}
