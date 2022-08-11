package online.store.onlineBookStore.models.entities.dtos;


import online.store.onlineBookStore.enums.CategoryEnum;
import online.store.onlineBookStore.enums.CoverTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDTO {

    private Long id;

    @NotBlank(message = "Title cannot be empty!")
    @Size(min = 3, message = "The title must be at least 3 characters!")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull (message = "Release date cannot be empty!")
    private LocalDate releaseDate;

    @NotBlank(message = "Publisher cannot be empty!")
    private String publisher;

    @NotBlank(message = "Must provide a picture URL!")
    private String pictureUrl;

    @NotNull(message = "Category cannot be empty!")
    private CategoryEnum category;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Select a cover type!")
    private CoverTypeEnum coverType;

    @NotNull(message = "Price cannot be empty!")
    @Positive(message = "Price must be a positive number!")
    private BigDecimal price;

    @NotBlank(message = "Pages field cannot be empty!")
    private String pages;

    @NotBlank(message = "ISNB field cannot be empty!")
    private String isbn;

    @NotNull(message = "Stock field cannot be empty!")
    @Min(1)
    private Integer stock;

    @Valid
    @NotNull(message = "Author field cannot be empty!")
    private AuthorDTO author;

    public BookDTO() {

    }

    public Long getId() {
        return id;
    }

    public BookDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public BookDTO setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public BookDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public CoverTypeEnum getCoverType() {
        return coverType;
    }

    public BookDTO setCoverType(CoverTypeEnum coverType) {
        this.coverType = coverType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPages() {
        return pages;
    }

    public BookDTO setPages(String pages) {
        this.pages = pages;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public BookDTO setStock(Integer stock) {
        this.stock = stock;
        return this;
    }


    public AuthorDTO getAuthor() {
        return author;
    }

    public BookDTO setAuthor(AuthorDTO author) {
        this.author = author;
        return this;
    }
}