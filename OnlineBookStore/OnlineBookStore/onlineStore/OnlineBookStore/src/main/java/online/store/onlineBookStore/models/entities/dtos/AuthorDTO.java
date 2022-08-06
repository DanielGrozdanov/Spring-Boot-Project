package online.store.onlineBookStore.models.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDTO {

    @NotBlank(message = "Field cannot be empty!")
    @Size(min = 3,message = "Author name must be at least 3 characters!")
    private String authorName;

    public AuthorDTO() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public AuthorDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }
}
