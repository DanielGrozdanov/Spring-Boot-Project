package online.store.onlineBookStore.service;

import online.store.onlineBookStore.repositories.CartBooksRepository;
import online.store.onlineBookStore.services.CartBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CartBooksTest {


    private CartBookService cartBookService;


    @Mock
    private CartBooksRepository cartBooksRepository;

    @BeforeEach
    private void setup() {
        cartBookService = new CartBookService(cartBooksRepository);
    }

    @Test
    void purgeTable(){
        cartBookService.purgeCartBooksTable();
        verify(cartBooksRepository).deleteAll();
    }
}
