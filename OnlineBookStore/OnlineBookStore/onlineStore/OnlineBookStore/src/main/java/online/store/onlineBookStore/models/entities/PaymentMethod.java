package online.store.onlineBookStore.models.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "payment_methods")
public class PaymentMethod extends BaseEntity{

    @Column(name = "payment_type")
    private String paymentType;

    @Column(nullable = false,name = "card_number")
    private String cardNumber;

    @Column(nullable = false,name = "expiration_month")
    private String expiryMonth;

    @Column(nullable = false,name = "expiration_year")
    private Integer expiryYear;


    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String cvc;

    @OneToMany
    private List<Order> order;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;


    public PaymentMethod() {

    }

    public String getPaymentType() {
        return paymentType;
    }

    public PaymentMethod setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public PaymentMethod setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getOwner() {
        return owner;
    }

    public PaymentMethod setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getCvc() {
        return cvc;
    }

    public PaymentMethod setCvc(String cvc) {
        this.cvc = cvc;
        return this;
    }

    public List<Order> getOrder() {
        return order;
    }

    public PaymentMethod setOrder(List<Order> order) {
        this.order = order;
        return this;
    }

    public User getUser() {
        return user;
    }

    public PaymentMethod setUser(User user) {
        this.user = user;
        return this;
    }
}
