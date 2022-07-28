package online.store.onlineBookStore.models.viewModel;


import online.store.onlineBookStore.models.entities.Author;
import online.store.onlineBookStore.models.entities.Category;
import online.store.onlineBookStore.models.enums.CoverTypeEnum;


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

    private String isbn;

    private Author authorName;


    public BookViewModel() {

    }

    public Long getId() {
        return id;
    }

    public BookViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
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
