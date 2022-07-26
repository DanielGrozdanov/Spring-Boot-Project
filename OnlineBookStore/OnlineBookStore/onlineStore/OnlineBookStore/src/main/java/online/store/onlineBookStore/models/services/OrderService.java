package online.store.onlineBookStore.models.services;

import online.store.onlineBookStore.models.entities.*;
import online.store.onlineBookStore.models.filepath.FilePath;
import online.store.onlineBookStore.models.repositories.BookRepository;
import online.store.onlineBookStore.models.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final CartBookService cartBookService;
    private final ModelMapper modelMapper;
    private final PaymentMethodService paymentMethodService;
    private final DeliveryService deliveryService;
    private final BookRepository bookRepository;


    @Autowired
    public OrderService(UserService userService, OrderRepository orderRepository, CartBookService cartBookService, ModelMapper modelMapper
            , PaymentMethodService paymentMethodService1, DeliveryService deliveryService, BookRepository bookRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.cartBookService = cartBookService;
        this.modelMapper = modelMapper;
        this.paymentMethodService = paymentMethodService1;
        this.deliveryService = deliveryService;
        this.bookRepository = bookRepository;
    }

    public Order getOrder(Principal principal) {
        String name = principal.getName();
        User loggedUser = this.userService.findByUsername(name);

        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setPaymentMethod(paymentMethodService.findByUser(loggedUser));
        order.setDelivery(deliveryService.findByUser(loggedUser));
        double totalAmount = 0.00;

        List<CartBooks> cartBooksList = this.cartBookService.checkIfThereAreBooks();
        for (CartBooks cartBooks : cartBooksList) {
            Integer amount = cartBooks.getAmount();
            BigDecimal price = cartBooks.getBook().getPrice();
            totalAmount += amount * Double.parseDouble(price.toString());
            Book book = cartBooks.getBook();
            Integer currentStock = book.getStock();
            book.setStock(currentStock - amount);
            this.bookRepository.save(book);

        }
        order.setTotalValue(BigDecimal.valueOf(totalAmount));
        User user = this.userService.findByUsername(principal.getName());
        order.setClient(user);
        return order;
    }

    public void save(Order order) {
        Order orderCompleted = this.orderRepository.saveAndFlush(order);
        writeOrderToFile(orderCompleted);
    }

    private void writeOrderToFile(Order order) {

        FileWriter fw;
               try {

                   fw = new FileWriter(FilePath.FILE_PATH);

                   BufferedWriter bw = new BufferedWriter(fw);

                   StringBuilder books = new StringBuilder(System.lineSeparator());
                   List<CartBooks> booksList = order.getBooks();
                   for (CartBooks cartBooks : booksList) {
                       books.append("  #Book name :  ").append(cartBooks.getBook().getTitle())
                               .append(System.lineSeparator())
                               .append("  #Book price : ").append(cartBooks.getBook().getPrice())
                               .append(System.lineSeparator())
                               .append("  #Author :  ").append(cartBooks.getBook().getAuthor())
                               .append(System.lineSeparator())
                               .append("  #Amount of books : ").append(cartBooks.getAmount())
                               .append(System.lineSeparator());

                   }

                   String payment = System.lineSeparator() + "  #Payment Type: " + order.getPaymentMethod().getPaymentType() +
                           System.lineSeparator();

                   String content = System.lineSeparator() + "  #Order number :" + order.getId() +
                           System.lineSeparator() +
                           "  #Total Price: " + order.getTotalValue().doubleValue() + "€" +
                           System.lineSeparator() +
                           "--------------------------------------" +
                           "  #Client : " +
                           order.getClient().getFirstName() + " " + order.getClient().getLastName() +
                           System.lineSeparator() +
                           "  #Client phone number: " + order.getClient().getPhoneNumber() +
                           System.lineSeparator() +
                           "  #Client email: " + order.getClient().getEmail() +
                           System.lineSeparator() +
                           "  #Ordered books : " + books +
                           System.lineSeparator() +
                           "--------------------------------------" +
                           System.lineSeparator() +
                           "  #Payment information : " + payment +
                           System.lineSeparator() +
                           "--------------------------------------" +
                           System.lineSeparator() +
                           "  #Delivery information : " +
                           System.lineSeparator() +
                           " #Country :" + order.getDelivery().getCountry() +
                           System.lineSeparator() +
                           "  #City : " + order.getDelivery().getCity() +
                           System.lineSeparator() +
                           "  #Address: " + order.getDelivery().getAddress() +
                           System.lineSeparator() +
                           "  #Phone : " + order.getDelivery().getPhone() +
                           System.lineSeparator() +
                           "  #Email : " + order.getDelivery().getEmail() +
                           System.lineSeparator() +
                           "  #Courier :" + order.getDelivery().getCourier() +
                           System.lineSeparator();
                   bw.write(content);
                   bw.close();
               }catch (Exception e){
                   e.printStackTrace();
               }
    }
}
/*
    StringBuilder buildOrder = new StringBuilder(System.lineSeparator());
                   buildOrder.append("  #Order number :").append(order.getId())
                           .append(System.lineSeparator())
                           .append("  #Total Price: ").append(order.getTotalValue().doubleValue()).append("€")
                           .append(System.lineSeparator())
                           .append("--------------------------------------")
                           .append("  #Client : ")
                           .append(order.getClient().getFirstName()).append(" ").append(order.getClient().getLastName())
                           .append(System.lineSeparator())
                           .append("  #Client phone number: ").append(order.getClient().getPhoneNumber())
                           .append(System.lineSeparator())
                           .append("  #Client email: ").append(order.getClient().getEmail())
                           .append(System.lineSeparator())
                           .append("  #Ordered books : ").append(books)
                           .append(System.lineSeparator())
                           .append("--------------------------------------")
                           .append(System.lineSeparator())
                           .append("  #Payment information : ").append(payment)
                           .append(System.lineSeparator())
                           .append("--------------------------------------")
                           .append(System.lineSeparator())
                           .append("  #Delivery information : ")
                           .append(System.lineSeparator())
                           .append(" #Country :").append(order.getDelivery().getCountry())
                           .append(System.lineSeparator())
                           .append("  #City : ").append(order.getDelivery().getCity())
                           .append(System.lineSeparator())
                           .append("  #Address: ").append(order.getDelivery().getAddress())
                           .append(System.lineSeparator())
                           .append("  #Phone : ").append(order.getDelivery().getPhone())
                           .append(System.lineSeparator())
                           .append("  #Email : ").append(order.getDelivery().getEmail())
                           .append(System.lineSeparator())
                           .append("  #Courier :").append(order.getDelivery().getCourier())
                           .append(System.lineSeparator());
*/
