package online.store.onlineBookStore.models.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @ManyToOne
    private User user;

    private String status = "open";

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private Set<CartBooks> cart;

    public Cart() {

    }

    public User getUser() {
        return user;
    }

    public Cart setUser(User user) {
        this.user = user;
        return this;
    }

    public Set<CartBooks> getCart() {
        return cart;
    }

    public String getStatus() {
        return status;
    }

    public Cart setStatus(String status) {
        this.status = status;
        return this;
    }

    public Cart setCart(Set<CartBooks> cart) {
        this.cart = cart;
        return this;
    }
}
