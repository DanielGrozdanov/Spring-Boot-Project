package online.store.onlineBookStore.repositories;

import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartBooksRepository extends JpaRepository<CartBooks,Long> {


    List<CartBooks> findByCartId(Long cartId);

    CartBooks findByBookIdAndCartId(Long bookId, Long cartId);

    List<CartBooks> findByCart(User loggedUser);
}
