package online.store.onlineBookStore.models.services;


import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.repositories.DeliveryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    private final ModelMapper modelMapper;
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(ModelMapper modelMapper, DeliveryRepository deliveryRepository) {
        this.modelMapper = modelMapper;
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery saveToDB(Delivery delivery){
        return this.deliveryRepository.saveAndFlush(delivery);
    }
    public Delivery findByUser(User user){
        String username = user.getUsername();
        return this.deliveryRepository.findByUserUsername(username).get();
    }

}
