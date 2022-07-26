package online.store.onlineBookStore.models.web;

import online.store.onlineBookStore.models.services.CartBookService;
import online.store.onlineBookStore.models.services.DeliveryService;
import online.store.onlineBookStore.models.services.OrderService;
import online.store.onlineBookStore.models.services.PaymentMethodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final CartBookService cartBookService;
    private final DeliveryService deliveryService;
    private final PaymentMethodService paymentMethodService;
    private final OrderService orderService;


    public OrderController(CartBookService cartBookService, DeliveryService deliveryService, PaymentMethodService paymentMethodService, OrderService orderService) {
        this.cartBookService = cartBookService;
        this.deliveryService = deliveryService;
        this.paymentMethodService = paymentMethodService;
        this.orderService = orderService;
    }

}