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
    private String expiryMonth;

    @NotNull(message = "Field cannot be empty.")
    @Min(value = 1,message = "Year cannot be less than 1")
    private Integer expiryYear;

    public PaymentMethodDTO() {

    }

    public String getPaymentType() {
        return paymentType;
    }

    public PaymentMethodDTO setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public PaymentMethodDTO setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
        return this;
    }

    public Integer getExpiryYear() {
        return expiryYear;
    }

    public PaymentMethodDTO setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
        return this;
    }
}
