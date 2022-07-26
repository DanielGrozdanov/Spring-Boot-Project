package online.store.onlineBookStore.models.services;

import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.PaymentMethod;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.entities.serviceModels.PaymentMethodInfoServiceModel;
import online.store.onlineBookStore.models.repositories.PaymentMethodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final ModelMapper modelMapper;
    private final PaymentMethodRepository paymentMethodRepository;
    private final CartService cartService;



    public PaymentMethodService(ModelMapper modelMapper, PaymentMethodRepository paymentMethodRepository, CartService cartService) {
        this.modelMapper = modelMapper;
        this.paymentMethodRepository = paymentMethodRepository;
        this.cartService = cartService;
    }


    public PaymentMethod saveToDB(PaymentMethodInfoServiceModel paymentMethodInfoServiceModel, User user) {
        PaymentMethod paymentMethod = modelMapper.map(paymentMethodInfoServiceModel,PaymentMethod.class);
        paymentMethod.setUser(user);
        this.paymentMethodRepository.save(paymentMethod);
        return paymentMethod;
    }

    public PaymentMethod findByUser(User user){
        String username = user.getUsername();
        return this.paymentMethodRepository.findByUserUsername(username).get();
    }
}
