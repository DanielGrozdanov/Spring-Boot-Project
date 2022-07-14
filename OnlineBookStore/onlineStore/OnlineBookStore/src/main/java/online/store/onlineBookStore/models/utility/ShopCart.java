package online.store.onlineBookStore.models.utility;

import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.PaymentMethod;
import org.springframework.beans.factory.DisposableBean;

import java.util.List;

public class ShopCart implements DisposableBean {

    private List<ShopCartEntity> items;
    private Delivery delivery;
    private PaymentMethod paymentMethod;

    public ShopCart(List<ShopCartEntity> items) {
        this.items = items;
    }

    public List<ShopCartEntity> getItems() {
        return items;
    }

    public ShopCart setItems(List<ShopCartEntity> items) {
        this.items = items;
        return this;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public ShopCart setDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public ShopCart setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    @Override
    public void destroy() throws Exception {

    }
}