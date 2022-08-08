package online.store.onlineBookStore.repositories;


import online.store.onlineBookStore.models.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    @Query("SELECT id from Delivery ")
    Delivery findById();

  List<Delivery> findByUserUsername(String name);
}
