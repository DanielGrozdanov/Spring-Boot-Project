package online.store.onlineBookStore.models.services;

import online.store.onlineBookStore.models.entities.Book;
import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.repositories.CartBooksRepository;
import org.springframework.stereotype.Service;

@Service
public class CartBookService {

    private CartBooksRepository cartBooksRepository;

    public CartBookService(CartBooksRepository cartBooksRepository) {
        this.cartBooksRepository = cartBooksRepository;
    }

    public CartBooks findBookInCart(Book book, Cart cart) {
        return cartBooksRepository.findByBookIdAndCartId(book.getId(), cart.getId());
    }


   public void removeById(Long id){
       this.cartBooksRepository.deleteById(id);
   }
}
