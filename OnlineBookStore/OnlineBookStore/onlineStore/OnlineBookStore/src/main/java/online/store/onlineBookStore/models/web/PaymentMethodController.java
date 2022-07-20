
package online.store.onlineBookStore.models.web;

import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.services.DeliveryService;
import online.store.onlineBookStore.models.services.OrderService;
import online.store.onlineBookStore.models.services.PaymentMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentMethodController {

    private ModelMapper modelMapper;
    private DeliveryService deliveryService;
    private CartBooks cartBooks;

    public PaymentMethodController(ModelMapper modelMapper, DeliveryService deliveryService, CartBooks cartBooks) {
        this.modelMapper = modelMapper;
        this.deliveryService = deliveryService;
        this.cartBooks = cartBooks;
    }

}
