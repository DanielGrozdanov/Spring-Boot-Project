package online.store.onlineBookStore.repositories;

import online.store.onlineBookStore.models.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {


    List<PaymentMethod> findByUserUsername(String name);
}
