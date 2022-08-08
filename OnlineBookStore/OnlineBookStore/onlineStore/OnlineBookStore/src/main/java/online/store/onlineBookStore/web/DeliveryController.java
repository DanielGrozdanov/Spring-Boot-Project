package online.store.onlineBookStore.web;

import online.store.onlineBookStore.models.entities.CartBooks;
import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.entities.dtos.DeliveryDTO;
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
import java.util.List;

@Controller
@RequestMapping("/deliveries")
public class DeliveryController {

    private final ModelMapper modelMapper;
    private final DeliveryService deliveryService;
    private final CartBookService cartBookService;
    private final UserService userService;



    @Autowired
    public DeliveryController(ModelMapper modelMapper, DeliveryService deliveryService, CartBookService cartBookService, UserService userService) {
        this.modelMapper = modelMapper;
        this.deliveryService = deliveryService;
        this.cartBookService = cartBookService;
        this.userService = userService;
    }

    @GetMapping("/delivery-information")
    public String orderDetails() {
        List<CartBooks> cartBooks = this.cartBookService.checkIfThereAreBooks();
        if (cartBooks.size() == 0) {
            return "redirect:/books/view/all";
        } else {
            return "delivery-information";
        }
    }

    @PostMapping("/delivery-information")
    public String deliveryDetailsInput(@Valid DeliveryDTO deliveryDTO, BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("deliveryDTO", deliveryDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.deliveryDTO", bindingResult);
            return "redirect:/deliveries/delivery-information";
        }

        String user = principal.getName();
        User loggedUser = this.userService.findByUsername(user);

        Delivery delivery = this.modelMapper.map(deliveryDTO,Delivery.class);
        delivery.setOrders(new ArrayList<>());
        delivery.setUser(loggedUser);
        this.deliveryService.saveToDB(delivery);

        return "redirect:/payments/payment-information";
    }
    @ModelAttribute("deliveryDTO")
    public DeliveryDTO deliveryDTO() {
        return new DeliveryDTO();
    }
}
