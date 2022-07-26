package online.store.onlineBookStore.models.repositories;

import online.store.onlineBookStore.models.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    Optional<PaymentMethod> findByUserUsername(String name);
}
