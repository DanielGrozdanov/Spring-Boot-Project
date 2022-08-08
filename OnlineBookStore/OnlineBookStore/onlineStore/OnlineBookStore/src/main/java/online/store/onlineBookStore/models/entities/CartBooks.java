package online.store.onlineBookStore.models.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart_books")
public class CartBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(optional = false,cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Integer amount = 1;


    public CartBooks() {

    }


    public CartBooks(Book book, Cart cart) {
        this.book = book;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public CartBooks setId(Long id) {
        this.id = id;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public CartBooks setBook(Book book) {
        this.book = book;
        return this;
    }

    public Cart getCart() {
        return cart;
    }

    public CartBooks setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public CartBooks setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartBooks cartBooks = (CartBooks) o;
        return Objects.equals(book, cartBooks.book) && Objects.equals(cart, cartBooks.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, cart);
    }
}
