package online.store.onlineBookStore.models.entities.dtos;


import javax.validation.constraints.*;

public class PaymentMethodDTO {


    private String paymentType;

    @Pattern(regexp = "^[0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}$",message = "Not a valid card number.")
    @NotBlank(message = "Card number cannot be empty.")
    private String cardNumber;

    @NotBlank(message = "Field cannot be empty.")
    private String owner;

    @NotBlank(message = "Field cannot be empty.")
    @Size(min = 3,max = 3,message = "Field must contain 3 symbols.")
    private String cvc;

    @NotBlank(message = "Field cannot be empty.")
    private String expiryDate;

    @NotBlank(message = "Field cannot be empty.")
    private String expirationMonth;

    @NotNull(message = "Field cannot be empty.")
    @Min(value = 1,message = "Year cannot be less than 1")
    private Integer expirationYear;

    public PaymentMethodDTO() {

    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getCvc() {
        return cvc;
    }

    public PaymentMethodDTO setCvc(String cvc) {
        this.cvc = cvc;
        return this;
    }

    public PaymentMethodDTO setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public PaymentMethodDTO setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public PaymentMethodDTO setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public PaymentMethodDTO setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public PaymentMethodDTO setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
        return this;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public PaymentMethodDTO setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
        return this;
    }
}
