package online.store.onlineBookStore.models.entities.dtos;

import online.store.onlineBookStore.models.enums.GenderEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

public class UserRegisterDTO {

    @NotBlank(message = "Username cannot be empty!")
    @Size(min = 4,max = 20,message = "Username must be between 4 and 20 characters!")
    private String username;

    @NotBlank(message = "First name cannot be empty!")
    @Size(min = 2,max = 20,message = "First name must be between 2 and 20 characters!")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty!")
    @Size(min = 2,max = 20,message = "Last name must be between 2 and 20 characters!")
    private String lastName;

    @NotNull(message = "Age cannot be empty!")
    @Positive(message = "Age is invalid!")
    @Min(value = 0,message = "Age must be between 0 and 99")
    @Max(value = 99)
    private Integer age;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Invalid email!")
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 4,message = "Password must contain at least 4 characters!")
    private String password;

    @NotBlank(message = "Confirm password cannot be empty!")
    @Size(min = 4,message = "Confirm Password must contain at least 4 characters!")
    private String confirmPassword;


    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please select your gender!")
    private GenderEnum gender;

    public UserRegisterDTO() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegisterDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge(){
            return age;
    }

    public UserRegisterDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserRegisterDTO setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
