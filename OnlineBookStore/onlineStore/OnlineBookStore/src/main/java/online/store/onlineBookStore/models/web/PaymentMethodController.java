package online.store.onlineBookStore.models.web;

import online.store.onlineBookStore.models.services.OrderService;
import online.store.onlineBookStore.models.services.PaymentMethodService;
import online.store.onlineBookStore.models.utility.ShopCart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentMethodController {

    private final ModelMapper modelMapper;
    private final ShopCart shopCart;
    private final OrderService orderService;
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(ModelMapper modelMapper, ShopCart shopCart, OrderService orderService, PaymentMethodService paymentMethodService) {
        this.modelMapper = modelMapper;
        this.shopCart = shopCart;
        this.orderService = orderService;
        this.paymentMethodService = paymentMethodService;
    }
}
