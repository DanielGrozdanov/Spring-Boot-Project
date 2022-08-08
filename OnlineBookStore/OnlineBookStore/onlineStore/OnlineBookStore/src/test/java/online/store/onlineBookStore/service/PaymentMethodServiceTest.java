package online.store.onlineBookStore.service;


import online.store.onlineBookStore.enums.GenderEnum;
import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.*;
import online.store.onlineBookStore.models.entities.dtos.DeliveryDTO;
import online.store.onlineBookStore.models.entities.serviceModels.PaymentMethodInfoServiceModel;
import online.store.onlineBookStore.repositories.PaymentMethodRepository;
import online.store.onlineBookStore.services.PaymentMethodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PaymentMethodServiceTest {

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private PaymentMethodService paymentMethodService;
    private ModelMapper modelMapper;
    private User testUser;
    private Role testRole;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        paymentMethodService = new PaymentMethodService(modelMapper, paymentMethodRepository);
        testUser = new User();
        testRole = new Role();
        testRole.setName(RoleEnum.USER);
        testUser.setUsername("UserT")
                .setFirstName("Pesho")
                .setLastName("Peshov")
                .setAge(13)
                .setEmail("peshov@gmail.com")
                .setPassword("aaaaa")
                .setGender(GenderEnum.MALE)
                .setPhoneNumber("+35111111111")
                .setRole(testRole);
    }

    @Test
    public void save() {
        PaymentMethodInfoServiceModel paymentMethod = new PaymentMethodInfoServiceModel();
        User testUser = this.testUser;
        paymentMethod.setPaymentType("Credit Card");
        paymentMethod.setCardNumber("2222 1111 3333 4444");
        paymentMethod.setExpiryMonth("January");
        paymentMethod.setExpiryYear(2024);
        paymentMethod.setOwner("Some Owner");
        List<Order> orderList = new ArrayList<>();
        paymentMethod.setOrder(orderList);
        paymentMethodService.saveToDB(paymentMethod, testUser);
        PaymentMethod mapPayment = this.modelMapper.map(paymentMethod, PaymentMethod.class);
        mapPayment.setUser(testUser);


        ArgumentCaptor<PaymentMethod> paymentMethodArgumentCaptor = ArgumentCaptor.forClass(PaymentMethod.class);
        verify(paymentMethodRepository).save(paymentMethodArgumentCaptor.capture());

        PaymentMethod current = paymentMethodArgumentCaptor.getValue();

        Assertions.assertEquals(current.getPaymentType(), paymentMethod.getPaymentType());
    }

    @Test
    public void findByUser() {
        PaymentMethodInfoServiceModel paymentMethod = new PaymentMethodInfoServiceModel();
        User testUser = this.testUser;
        paymentMethod.setPaymentType("Credit Card1");
        paymentMethod.setCardNumber("2222 3333 4444 1111");
        paymentMethod.setExpiryMonth("October");
        paymentMethod.setExpiryYear(2031);
        paymentMethod.setOwner("OwnerOwner");
        List<Order> orderList = new ArrayList<>();
        paymentMethod.setOrder(orderList);
        paymentMethodService.saveToDB(paymentMethod, testUser);
        PaymentMethod mapPayment = this.modelMapper.map(paymentMethod, PaymentMethod.class);
        mapPayment.setUser(testUser);

        when(paymentMethodRepository.findByUserUsername(testUser.getUsername())).thenReturn(List.of(mapPayment));

        PaymentMethod byUser = paymentMethodService.findByUser(mapPayment.getUser());

        Assertions.assertEquals(byUser.getCvc(), paymentMethod.getCvc());
    }

    @Test
    void purgeTable() {
        paymentMethodService.purgePaymentMethodTable();
        verify(paymentMethodRepository).deleteAll();
    }
}
