package online.store.onlineBookStore.models.viewModel;


import online.store.onlineBookStore.models.entities.Category;
import online.store.onlineBookStore.models.entities.Order;
import online.store.onlineBookStore.models.enums.CoverTypeEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookViewModel {

    private String title;

    private LocalDate releaseDate;

    private String publisher;

    private Category category;

    private CoverTypeEnum coverType;

    private BigDecimal price;

    private String isbn;

    private String authorName;

    private List<Order> order;

    public BookViewModel() {

    }

    public String getTitle() {
        return title;
    }

    public BookViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public BookViewModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookViewModel setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public BookViewModel setCategory(Category category) {
        this.category = category;
        return this;
    }

    public CoverTypeEnum getCoverType() {
        return coverType;
    }

    public BookViewModel setCoverType(CoverTypeEnum coverType) {
        this.coverType = coverType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookViewModel setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookViewModel setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public List<Order> getOrder() {
        return order;
    }

    public BookViewModel setOrder(List<Order> order) {
        this.order = order;
        return this;
    }
}
