package online.store.onlineBookStore.service;

import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.Order;
import online.store.onlineBookStore.models.entities.PaymentMethod;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.repositories.*;
import online.store.onlineBookStore.services.*;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private OrderService orderService;
    private ModelMapper modelMapper;


    @Mock
    private CartBookService cartBookService;

    @Mock
    private DeliveryService deliveryService;

    @Mock
    private PaymentMethodService paymentMethodService;

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;


    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CartBooksRepository cartBooksRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    public void setUp() {
        modelMapper = new ModelMapper();
        deliveryService = new DeliveryService(deliveryRepository);
        paymentMethodService = new PaymentMethodService(modelMapper, paymentMethodRepository);
        userService = new UserService(userRepository, modelMapper, passwordEncoder, roleService);
        orderService =
                new OrderService(userService,
                        orderRepository,
                        paymentMethodService,
                        deliveryService,
                        bookRepository,
                        cartRepository,
                        cartService,
                        cartBooksRepository);

    }

    @Test
    void save() {
        Order order = new Order();
        order.setBooks(new ArrayList<>());
        order.setPaymentMethod(new PaymentMethod());
        order.setDelivery(new Delivery());
        order.setDate(LocalDate.now());
        order.setTotalValue(BigDecimal.valueOf(39.99));
        order.setClient(new User());
        order.setStatus("pending");
        orderService.save(order);

        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).saveAndFlush(orderArgumentCaptor.capture());
        Order order1 = orderArgumentCaptor.getValue();
        assertThat(order1.getTotalValue()).isEqualTo(order.getTotalValue());

    }

    @Test
    void purgeTable() {
        orderService.purgeOrdersTable();
        verify(orderRepository).deleteAll();
    }
}
