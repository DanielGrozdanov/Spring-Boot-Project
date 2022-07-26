package online.store.onlineBookStore.models.repositories;


import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    @Query("SELECT id from Delivery ")
    Delivery findById();

    Optional<Delivery> findByUserUsername(String name);
}
