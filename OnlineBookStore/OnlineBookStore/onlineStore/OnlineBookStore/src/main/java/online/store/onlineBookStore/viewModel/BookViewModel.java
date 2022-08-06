package online.store.onlineBookStore.viewModel;


import online.store.onlineBookStore.models.entities.Author;
import online.store.onlineBookStore.models.entities.Category;
import online.store.onlineBookStore.enums.CoverTypeEnum;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookViewModel {

    private Long id;

    private String title;

    private String pictureUrl;

    private LocalDate releaseDate;

    private String publisher;

    private Category category;

    private CoverTypeEnum coverType;

    private BigDecimal price;

    private String pages;

    private String isbn;

    private Author authorName;

    private Integer stock;

    public BookViewModel() {

    }

    public Long getId() {
        return id;
    }

    public String getPages() {
        return pages;
    }

    public BookViewModel setPages(String pages) {
        this.pages = pages;
        return this;
    }

    public BookViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStock() {
        return stock;
    }

    @NotNull(message = "Stock field cannot be empty!")
    @Min(1)
    public BookViewModel setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public BookViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public BookViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    @NotNull(message = "Price cannot be empty!")
    @Positive(message = "Price must be a positive number!")
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

    public Author getAuthorName() {
        return authorName;
    }

    public BookViewModel setAuthorName(Author authorName) {
        this.authorName = authorName;
        return this;
    }

}
