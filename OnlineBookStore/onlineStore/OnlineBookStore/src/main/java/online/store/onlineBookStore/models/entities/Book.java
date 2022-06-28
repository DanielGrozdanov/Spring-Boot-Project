package online.store.onlineBookStore.models.entities;

import online.store.onlineBookStore.models.enums.BookCategoryEnum;
import online.store.onlineBookStore.models.enums.CoverTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCategoryEnum category;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String publisher;

    @Enumerated(EnumType.STRING)
    private CoverTypeEnum coverType;

    @Column(nullable = false)
    private String pages;

    @Column(nullable = false,unique = true)
    private String isbn;

    @ManyToOne
    private Author author;

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookCategoryEnum getCategory() {
        return category;
    }

    public Book setCategory(BookCategoryEnum category) {
        this.category = category;
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

    public String getPages() {
        return pages;
    }

    public Book setPages(String pages) {
        this.pages = pages;
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
}
