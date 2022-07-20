package online.store.onlineBookStore.models.viewModel;


import online.store.onlineBookStore.models.entities.Book;

import java.math.BigDecimal;

public class CartViewModel {

    private Book book;

    private BigDecimal price;

    private Integer amount;

    public CartViewModel() {

    }

    public Book getBook() {
        return book;
    }

    public CartViewModel setBook(Book book) {
        this.book = book;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CartViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public CartViewModel setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }
}
