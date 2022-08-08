package online.store.onlineBookStore.repositories;

import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUserAndStatus(User user, String status);

    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.status = ?1")
    void setStatus(String status);


    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.status = ?1")
    void deleteCartByStatus(String status);



}