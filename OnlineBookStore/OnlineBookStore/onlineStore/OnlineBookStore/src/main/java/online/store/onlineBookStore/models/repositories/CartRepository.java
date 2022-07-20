package online.store.onlineBookStore.models.repositories;

import online.store.onlineBookStore.models.entities.Cart;
import online.store.onlineBookStore.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUserAndStatus(User user, String status);

}