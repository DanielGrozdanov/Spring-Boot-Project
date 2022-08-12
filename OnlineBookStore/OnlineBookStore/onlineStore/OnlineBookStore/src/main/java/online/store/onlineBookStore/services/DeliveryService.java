package online.store.onlineBookStore.services;


import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.repositories.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery saveToDB(Delivery delivery){
        return this.deliveryRepository.saveAndFlush(delivery);
    }

    public Delivery findByUser(User user){
        String username = user.getUsername();
        List<Delivery> deliveries = this.deliveryRepository.findByUserUsername(username);
        return deliveries.get(deliveries.size() - 1);
    }

    public void purgeDeliveryInformationTable() {
        this.deliveryRepository.deleteAll();
    }

    public String findById(Long id) throws Exception {
        Delivery delivery = this.deliveryRepository.findById(id).orElseThrow(Exception::new);
        return delivery.getCourier();
    }
}
