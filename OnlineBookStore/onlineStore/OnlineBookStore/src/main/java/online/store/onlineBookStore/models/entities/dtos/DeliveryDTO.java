package online.store.onlineBookStore.models.entities.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DeliveryDTO {

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 4 ,message = "Field must be at least 4 characters.")
    private String person;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 3,message = "Field must be at least 3 characters.")
    private String country;

    @NotBlank(message = "Field cannot be empty")
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",message = "Phone number is invalid.")
    private String phone;

    @NotBlank(message = "Field cannot be empty")
    @Email(message = "Invalid email.")
    private String email;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 3,message = "Field must be at least 3 characters.")
    private String city;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 5,message = "Field must be at least 5 characters.")
    private String address;

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 2,message = "Field must be at least 2 characters.")
    private String postalCode;

    @NotBlank(message = "Field cannot be empty")
    private String courier;

    public DeliveryDTO() {
    }

    public String getPerson() {
        return person;
    }

    public DeliveryDTO setPerson(String person) {
        this.person = person;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public DeliveryDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public DeliveryDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DeliveryDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCity() {
        return city;
    }

    public DeliveryDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public DeliveryDTO setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCourier() {
        return courier;
    }

    public DeliveryDTO setCourier(String courier) {
        this.courier = courier;
        return this;
    }
}
