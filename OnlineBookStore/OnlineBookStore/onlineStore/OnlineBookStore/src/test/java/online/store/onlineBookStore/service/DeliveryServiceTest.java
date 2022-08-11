package online.store.onlineBookStore.service;

import online.store.onlineBookStore.enums.GenderEnum;
import online.store.onlineBookStore.enums.RoleEnum;
import online.store.onlineBookStore.models.entities.Delivery;
import online.store.onlineBookStore.models.entities.Order;
import online.store.onlineBookStore.models.entities.Role;
import online.store.onlineBookStore.models.entities.User;
import online.store.onlineBookStore.models.entities.dtos.DeliveryDTO;
import online.store.onlineBookStore.repositories.DeliveryRepository;

import online.store.onlineBookStore.services.DeliveryService;

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


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {


    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private DeliveryService deliveryService;
    private ModelMapper modelMapper;
    private User testUser;
    private Role testRole;


    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        deliveryService = new DeliveryService(deliveryRepository);
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
        Delivery delivery = new Delivery();
        delivery.setPerson("Person");
        delivery.setCountry("SomeCountry");
        delivery.setPhone("+359222222222");
        delivery.setEmail("personEmail@gmai.com");
        delivery.setCity("PersonCity");
        delivery.setAddress("PersonAddress");
        delivery.setPostalCode("2345");
        delivery.setCourier("Courier");
        delivery.setUser(testUser);
        List<Order> orderList = new ArrayList<>();
        delivery.setOrders(orderList);
        this.deliveryService.saveToDB(delivery);

        ArgumentCaptor<Delivery> deliveryArgumentCaptor = ArgumentCaptor.forClass(Delivery.class);
        verify(deliveryRepository).saveAndFlush(deliveryArgumentCaptor.capture());

        Delivery delivery1 = deliveryArgumentCaptor.getValue();
        assertThat(delivery1.getPerson()).isEqualTo(delivery.getPerson());
    }


    @Test
    public void findByUser() {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setPerson("Person");
        deliveryDTO.setCountry("SomeCountry");
        deliveryDTO.setPhone("+359444444444");
        deliveryDTO.setEmail("someenail@gmail.com");
        deliveryDTO.setCity("SomeCity");
        deliveryDTO.setAddress("SomeAddress");
        deliveryDTO.setPostalCode("2341");
        deliveryDTO.setCity("SomeCountry");
        deliveryDTO.setCourier("theCourier");
        Delivery delivery = this.modelMapper.map(deliveryDTO, Delivery.class);
        delivery.setUser(testUser);
        deliveryService.saveToDB(delivery);

        when(deliveryRepository.findByUserUsername(testUser.getUsername())).thenReturn(List.of(delivery));

        Delivery byUser = deliveryService.findByUser(delivery.getUser());

        Assertions.assertEquals(byUser,delivery);
    }

    @Test
    void purgeTable(){
        deliveryService.purgeDeliveryInformationTable();
        verify(deliveryRepository).deleteAll();
    }
}
