package online.store.onlineBookStore.models.utility;

import online.store.onlineBookStore.models.entities.Book;

import java.math.BigDecimal;

public class ShopCartEntity {
    private Book book;
    private BigDecimal cost;

    public Book getBook() {
        return book;
    }

    public ShopCartEntity setBook(Book book) {
        this.book = book;
        return this;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public ShopCartEntity setCost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }
}