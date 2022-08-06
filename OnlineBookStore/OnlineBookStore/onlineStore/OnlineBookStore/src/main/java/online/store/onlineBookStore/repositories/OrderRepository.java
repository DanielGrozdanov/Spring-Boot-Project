package online.store.onlineBookStore.repositories;

import online.store.onlineBookStore.models.entities.Order;
import online.store.onlineBookStore.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order getByStatus(String opened);

    Order findByClientAndStatus(User currentUser, String status);

/*    @Modifying
    @Transactional
    void cleanOrders();*/
}
