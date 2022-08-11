
package online.store.onlineBookStore.web;

import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.Order;
import online.store.onlineBookStore.models.entities.PaymentMethod;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.entities.dtos.DeliveryDTO;
import online.store.onlineBookStore.models.entities.serviceModels.PaymentMethodInfoServiceModel;
import online.store.onlineBookStore.models.entities.dtos.PaymentMethodDTO;

import online.store.onlineBookStore.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/payments")
public class PaymentMethodController {

    private final ModelMapper modelMapper;
    private final DeliveryService deliveryService;
    private final PaymentMethodService paymentMethodService;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public PaymentMethodController(ModelMapper modelMapper, DeliveryService deliveryService, CartBookService cartBookService, PaymentMethodService paymentMethodService, CartService cartService, UserService userService, OrderService orderService) {
        this.modelMapper = modelMapper;
        this.deliveryService = deliveryService;
        this.paymentMethodService = paymentMethodService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/payment-information")
    public String payment(Principal principal) {
        User user = this.userService.findByUsername(principal.getName());
        Delivery deliveryServiceByUser = this.deliveryService.findByUser(user);
        if (deliveryServiceByUser == null){
            return "redirect:/delivery-information";
        }
        return "payment-information";
    }

    @PostMapping("/payment-information")
    public String paymentInfoPost(@Valid PaymentMethodDTO paymentMethodDTO, BindingResult bindingResult
            ,RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("paymentMethodDTO", paymentMethodDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.paymentMethodDTO", bindingResult);
            return "redirect:/payments/payment-information";
        }

        PaymentMethodInfoServiceModel paymentMethodInfoServiceModel = new PaymentMethodInfoServiceModel();
        if (paymentMethodDTO.getPaymentType() == null) {

            paymentMethodInfoServiceModel = modelMapper.map(paymentMethodDTO, PaymentMethodInfoServiceModel.class);
            paymentMethodInfoServiceModel.setPaymentType("Credit Card");
            paymentMethodInfoServiceModel.setOrder(new ArrayList<>());

        } else {
            paymentMethodInfoServiceModel.setPaymentType("Bank Transfer");
            paymentMethodInfoServiceModel.setCvc("N/A");
            paymentMethodInfoServiceModel.setCardNumber("0000 0000 0000 0000");
            paymentMethodInfoServiceModel.setOwner("N/A");
            paymentMethodInfoServiceModel.setExpiryMonth("1");
            paymentMethodInfoServiceModel.setExpiryYear(1);
        }

        String user = principal.getName();
        User loggedUser = this.userService.findByUsername(user);
        this.paymentMethodService.saveToDB(paymentMethodInfoServiceModel, loggedUser);

        return "redirect:/payments/order-completed";
    }
    @ModelAttribute("paymentMethodDTO")
    public PaymentMethodDTO paymentMethodDTO() {
        return new PaymentMethodDTO();
    }



    @GetMapping("/order-completed")
    public String orderCompleted(Principal principal, RedirectAttributes redirectAttributes){

        String name = principal.getName();
        User currentUser = this.userService.findByUsername(name);
        PaymentMethod paymentByUser = this.paymentMethodService.findByUser(currentUser);
        Delivery deliveryByUser = this.deliveryService.findByUser(currentUser);

        if (paymentByUser == null){
            return "redirect:/deliveries/delivery-information";
        }
        if(deliveryByUser == null){
            return "redirect:/payments/payment-information";
        }


        Order order = null;
        try {
            order = this.orderService.getOrder(principal);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message","You have a book out of stock.");
            redirectAttributes.addFlashAttribute("message2","Cart is empty!!!");
            return "redirect:/payments/payment-information";
        }
        this.orderService.save(order);

        return "order-completed";
    }
}
