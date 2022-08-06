package online.store.onlineBookStore.schedulers;

import online.store.onlineBookStore.services.CartService;
import online.store.onlineBookStore.services.DeliveryService;
import online.store.onlineBookStore.services.OrderService;
import online.store.onlineBookStore.services.PaymentMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PurgeDataBaseScheduler {


    private static final Logger LOGGER = LoggerFactory.getLogger(PurgeDataBaseScheduler.class);
    private final CartService cartService;
    private final OrderService orderService;
    private final DeliveryService deliveryService;
    private final PaymentMethodService paymentMethodService;


    @Autowired
    public PurgeDataBaseScheduler(CartService cartService, OrderService orderService, DeliveryService deliveryService, PaymentMethodService paymentMethodService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.deliveryService = deliveryService;
        this.paymentMethodService = paymentMethodService;
    }

    @Scheduled(cron = "01 30 03 * * *")
    public void purgeCartDataBase(){
        this.cartService.purgeCartTable();
        LOGGER.info("Cart database maintenance completed.");
    }

    @Scheduled(cron = "01 32 03 * * *")
    public void purgeOrderDataBase(){
        this.orderService.purgeOrdersTable();
        LOGGER.info("Orders database maintenance completed.");
    }

    @Scheduled(cron = "01 34 03 * * *")
    public void purgeDeliveryDataBase(){
        this.deliveryService.purgeDeliveryInformationTable();
        LOGGER.info("Delivery information database maintenance completed.");
    }
    @Scheduled(cron = "01 36 03 * * *")
    public void purgePayMenMethodDataBase(){
        this.paymentMethodService.purgePaymentMethodTable();
        LOGGER.info("Payment Method database maintenance completed.");
    }
}
