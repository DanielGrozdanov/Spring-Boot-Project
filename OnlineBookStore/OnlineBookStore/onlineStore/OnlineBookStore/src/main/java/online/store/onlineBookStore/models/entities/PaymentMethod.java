package online.store.onlineBookStore.models.entities;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "payment_methods")
public class PaymentMethod extends BaseEntity{

    @Column(nullable = false,name = "payment_type")
    private String paymentType;

    @Column(nullable = false,name = "card_number")
    private String cardNumber;

    @Column(nullable = false,name = "expiration_date")
    private String expirationDate;

    @Column(nullable = false,name = "expiration_month")
    private String expirationMonth;

    @Column(nullable = false,name = "card_owner")
    private String cardOwner;

    @Column(nullable = false)
    private String cvc;

    @ManyToOne
    private Order order;


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

    public String getExpirationDate() {
        return expirationDate;
    }

    public PaymentMethod setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public PaymentMethod setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
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

    public Order getOrder() {
        return order;
    }

    public PaymentMethod setOrder(Order order) {
        this.order = order;
        return this;
    }
}
