package online.store.onlineBookStore.models.services;


import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.repositories.CartBooksRepository;
import online.store.onlineBookStore.models.repositories.CartRepository;
import online.store.onlineBookStore.models.repositories.UserRepository;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartBooksRepository cartBooksRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, CartBooksRepository cartBooksRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
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
}
