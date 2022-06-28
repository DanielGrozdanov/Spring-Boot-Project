package online.store.onlineBookStore.models.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod extends BaseEntity{

    @Column(nullable = false,name = "payment_type")
    private String paymentType;

    @Column(nullable = false,name = "card_number")
    private String cardNumber;

    @Column(nullable = false,name = "expiry_date")
    private LocalDate expiryDate;

    @Column(nullable = false)
    private String cardOwner;

    @Column(nullable = false)
    private String cvc;

    @OneToMany
    private List<Order> orders;

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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public PaymentMethod setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public PaymentMethod setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
        return this;
    }

    public String getCvc() {
        return cvc;
    }

    public PaymentMethod setCvc(String cvc) {
        this.cvc = cvc;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public PaymentMethod setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

}
