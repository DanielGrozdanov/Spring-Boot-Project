package online.store.onlineBookStore.models.entities;

import online.store.onlineBookStore.models.enums.CoverTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String title;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    public String picture;

    @ManyToOne
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CoverTypeEnum coverType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String pages;

    @Column(nullable = false,unique = true)
    private String isbn;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Order order;

    public Book() {

    }

    public String getPicture() {
        return picture;
    }

    public Book setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Book setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPages() {
        return pages;
    }

    public Book setPages(String pages) {
        this.pages = pages;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Book setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Book setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public CoverTypeEnum getCoverType() {
        return coverType;
    }

    public Book setCoverType(CoverTypeEnum coverType) {
        this.coverType = coverType;
        return this;
    }


    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Book setCategory(Category category) {
        this.category = category;
        return this;
    }
}
