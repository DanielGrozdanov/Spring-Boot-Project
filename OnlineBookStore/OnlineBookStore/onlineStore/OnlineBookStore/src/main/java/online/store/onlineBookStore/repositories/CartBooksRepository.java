package online.store.onlineBookStore.repositories;

import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartBooksRepository extends JpaRepository<CartBooks,Long> {


    List<CartBooks> findByCartId(Long cartId);

    CartBooks findByBookIdAndCartId(Long bookId, Long cartId);

    @Modifying
    @Transactional
    @Query("UPDATE CartBooks c SET c.status = ?1")
    void setStatus(String closed);


    CartBooks findCartBooksByStatusAndCart(String status, Cart cart);
}
