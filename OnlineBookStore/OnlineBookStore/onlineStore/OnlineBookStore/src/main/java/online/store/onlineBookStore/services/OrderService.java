package online.store.onlineBookStore.services;


import online.store.onlineBookStore.filepath.FilePath;
import online.store.onlineBookStore.models.entities.*;
import online.store.onlineBookStore.repositories.BookRepository;
import online.store.onlineBookStore.repositories.CartBooksRepository;
import online.store.onlineBookStore.repositories.CartRepository;
import online.store.onlineBookStore.repositories.OrderRepository;
import online.store.onlineBookStore.models.viewmodel.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final PaymentMethodService paymentMethodService;
    private final DeliveryService deliveryService;
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final CartBooksRepository cartBooksRepository;


    @Autowired
    public OrderService(UserService userService, OrderRepository orderRepository
            , PaymentMethodService paymentMethodService1,
                        DeliveryService deliveryService, BookRepository bookRepository, CartRepository cartRepository,
                        CartService cartService, CartBooksRepository cartBooksRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.paymentMethodService = paymentMethodService1;
        this.deliveryService = deliveryService;
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.cartBooksRepository = cartBooksRepository;
    }

    public Order getOrder(Principal principal) throws Exception {
        String name = principal.getName();
        User loggedUser = this.userService.findByUsername(name);


        double totalAmount = 0.00;

        List<CartBooks> cartBooksList = new ArrayList<>();
        Cart currentCart = this.cartService.validateCart(loggedUser);
        Set<CartBooks> cart = currentCart.getCart();

        if (cart.size() == 0) {
            throw new Exception("Cart is empty!!");
        }


        for (CartBooks cartBooks : cart) {
            Integer amount = cartBooks.getAmount();

            if (cartBooks.getBook().getStock() == 0) {
                throw new Exception("Book out of stock");
            }
            BigDecimal price = cartBooks.getBook().getPrice();
            totalAmount += amount * Double.parseDouble(price.toString());
            Book book = cartBooks.getBook();
            Integer currentStock = book.getStock();
            book.setStock(currentStock - amount);
            cartBooksList.add(cartBooks);
            this.bookRepository.save(book);

        }

        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setPaymentMethod(paymentMethodService.findByUser(loggedUser));
        order.setDelivery(deliveryService.findByUser(loggedUser));


        order.setBooks(cartBooksList);
        order.setTotalValue(BigDecimal.valueOf(totalAmount));
        User user = this.userService.findByUsername(principal.getName());
        order.setClient(user);
        return order;
    }


    public void save(Order order) {
        Order orderCompleted = this.orderRepository.saveAndFlush(order);
        this.cartRepository.setStatus("closed");
        this.cartBooksRepository.setStatus("closed");
        writeOrderToFile(orderCompleted);
    }

    private void writeOrderToFile(Order order) {
        FileWriter fw;
        try {
            fw = new FileWriter(FilePath.FILE_PATH.toString());


            StringBuilder books = new StringBuilder(System.lineSeparator());
            List<CartBooks> books1 = order.getBooks();
            for (CartBooks cartBooks : books1) {
                books.append(System.lineSeparator())
                        .append("%  #Book name :  ").append(cartBooks.getBook().getTitle())
                        .append(System.lineSeparator())
                        .append("%  #Book price : ").append(cartBooks.getBook().getPrice())
                        .append(System.lineSeparator())
                        .append("%  #Author :  ").append(cartBooks.getBook().getAuthor())
                        .append(System.lineSeparator())
                        .append("%  #Amount of books : ").append(cartBooks.getAmount())
                        .append(System.lineSeparator());

            }

            StringBuilder payment = new StringBuilder(System.lineSeparator());
            payment.append("%  #Payment Type:").append(order.getPaymentMethod().getPaymentType())
                    .append(System.lineSeparator());

            StringBuilder buildOrder = new StringBuilder(System.lineSeparator());
            buildOrder.append("  #Order number :").append(order.getId())
                    .append(System.lineSeparator())
                    .append("%  #Total Price:").append(order.getTotalValue().doubleValue()).append("€")
                    .append(System.lineSeparator())
                    .append("%--------------------------------------")
                    .append(System.lineSeparator())
                    .append("%  #Client :").append(order.getClient().getFirstName()).append(" ")
                    .append(order.getClient().getLastName())
                    .append(System.lineSeparator())
                    .append("%  #Client phone number:")
                    .append(order.getClient().getPhoneNumber())
                    .append(System.lineSeparator())
                    .append("%  #Client email:")
                    .append(order.getClient().getEmail())
                    .append(System.lineSeparator())
                    .append("%--------------------------------------")
                    .append(System.lineSeparator())
                    .append("%  #Ordered books :")
                    .append(books)
                    .append(System.lineSeparator())
                    .append("%--------------------------------------")
                    .append(System.lineSeparator())
                    .append(payment)
                    .append("%  #Payment information : ")
                    .append(System.lineSeparator())
                    .append("%  #Card Number :")
                    .append(order.getPaymentMethod().getCardNumber())
                    .append(System.lineSeparator())
                    .append("%  #Month Of Expiration :")
                    .append(order.getPaymentMethod().getExpiryMonth())
                    .append(System.lineSeparator())
                    .append("%  #Year Of Expiration : ")
                    .append(order.getPaymentMethod().getExpiryYear())
                    .append(System.lineSeparator())
                    .append("%  #Card Owner : ")
                    .append(order.getPaymentMethod().getOwner())
                    .append(System.lineSeparator())
                    .append(System.lineSeparator())
                    .append("%--------------------------------------")
                    .append(System.lineSeparator())
                    .append("%  #Delivery information :")
                    .append(System.lineSeparator())
                    .append("%  #Country :").append(order.getDelivery().getCountry())
                    .append(System.lineSeparator())
                    .append("%  #City :").append(order.getDelivery().getCity())
                    .append(System.lineSeparator())
                    .append("%  #Address:").append(order.getDelivery().getAddress())
                    .append(System.lineSeparator())
                    .append("%  #Phone :").append(order.getDelivery().getPhone())
                    .append(System.lineSeparator())
                    .append("%  #Email :").append(order.getDelivery().getEmail())
                    .append("%  #Courier :").append(order.getDelivery().getCourier())
                    .append(System.lineSeparator());

            String finalContent = buildOrder.toString();
            fw.write(finalContent);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void purgeOrdersTable() {
        this.orderRepository.deleteAll();
    }

    public List<OrderViewModel> findOrder() {
        List<Order> orders = this.orderRepository.findAll();
      return orders.stream().map(this::viewModel).collect(Collectors.toList());

    }
    private OrderViewModel viewModel(Order order) {
        try {
            OrderViewModel orderViewModel = new OrderViewModel();
            orderViewModel.setClient(this.userService.findById(order.getClient().getId()));
            orderViewModel.setDelivery(this.deliveryService.findById(order.getDelivery().getId()));
            orderViewModel.setPayment(this.paymentMethodService.findById(order.getPaymentMethod().getId()));
            orderViewModel.setTotalPrice(order.getTotalValue());
            orderViewModel.setId(order.getId());
            return orderViewModel;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}