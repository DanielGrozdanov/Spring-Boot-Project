package online.store.onlineBookStore.services;


import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.repositories.CartBooksRepository;
import online.store.onlineBookStore.repositories.CartRepository;
import online.store.onlineBookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartBooksRepository cartBooksRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartBooksRepository cartBooksRepository) {
        this.cartRepository = cartRepository;
        this.cartBooksRepository = cartBooksRepository;
    }

    public Cart validateCart(User user) {
        return this.cartRepository.findByUserAndStatus(user, "open");
    }


    public List<CartBooks> findCartContent(Long cartId) {
        return cartBooksRepository.findByCartId(cartId);
    }

    public void saveCart(CartBooks cartBooks, Cart cart) {
        this.cartRepository.save(cart);
        this.cartBooksRepository.save(cartBooks);
    }

    public void updateAmount(CartBooks bookInCart) {
        this.cartBooksRepository.save(bookInCart);
    }

    public void purgeCartTable() {
        this.cartRepository.deleteAll();
    }
}
