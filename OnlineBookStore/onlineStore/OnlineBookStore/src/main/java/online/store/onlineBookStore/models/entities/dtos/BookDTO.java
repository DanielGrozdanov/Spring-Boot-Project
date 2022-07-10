package online.store.onlineBookStore.models.entities.dtos;


import online.store.onlineBookStore.models.enums.CoverTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDTO {


    @NotBlank(message = "Title cannot be empty!")
    @Size(min = 3, message = "The title must be at least 3 characters!")
    private String title;

    @NotNull(message = "Price cannot be empty!")
    @Positive(message = "Price must be a positive number!")
    private BigDecimal price;

    @NotNull(message = "Category cannot be empty!")
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Select a cover type.")
    private CoverTypeEnum coverType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Release date cannot be empty!")
    private LocalDate releaseDate;

    @NotBlank(message = "Publisher cannot be empty!")
    private String publisher;

    @NotBlank(message = "ISNB number cannot be empty!")
    private String isbn;

    @NotBlank(message = "Author cannot be empty")
    private String authorName;

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public BookDTO setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public CoverTypeEnum getCoverType() {
        return coverType;
    }

    public BookDTO setCoverType(CoverTypeEnum coverType) {
        this.coverType = coverType;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public BookDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookDTO setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

}