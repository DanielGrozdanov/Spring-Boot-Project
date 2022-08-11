package online.store.onlineBookStore.models.entities.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDTO {


    private String name;

    public AuthorDTO() {
    }

    @Valid
    @NotBlank(message = "Field cannot be empty!")
    @Size(min = 3,message = "Author name must be at least 3 characters!")
    public String getName() {
        return name;
    }

    public AuthorDTO setName(String name) {
        this.name = name;
        return this;
    }
}
