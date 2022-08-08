package online.store.onlineBookStore.services;

import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.entities.serviceModels.PaymentMethodInfoServiceModel;
import online.store.onlineBookStore.models.entities.PaymentMethod;
import online.store.onlineBookStore.repositories.PaymentMethodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final ModelMapper modelMapper;
    private final PaymentMethodRepository paymentMethodRepository;




    public PaymentMethodService(ModelMapper modelMapper, PaymentMethodRepository paymentMethodRepository) {
        this.modelMapper = modelMapper;
        this.paymentMethodRepository = paymentMethodRepository;

    }


    public PaymentMethod saveToDB(PaymentMethodInfoServiceModel paymentMethodInfoServiceModel, User user) {
        PaymentMethod paymentMethod = modelMapper.map(paymentMethodInfoServiceModel,PaymentMethod.class);
        paymentMethod.setUser(user);
        this.paymentMethodRepository.save(paymentMethod);
        return paymentMethod;
    }

    public PaymentMethod findByUser(User user){
        String username = user.getUsername();
        List<PaymentMethod> paymentMethodsList = this.paymentMethodRepository.findByUserUsername(username);
        return paymentMethodsList.get(paymentMethodsList.size() - 1);
    }


    public void purgePaymentMethodTable() {
        this.paymentMethodRepository.deleteAll();
    }
}
