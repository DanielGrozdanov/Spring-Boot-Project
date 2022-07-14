package online.store.onlineBookStore.models.config;

import online.store.onlineBookStore.models.utility.ShopCart;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.ArrayList;

@Configuration
public class ApplicationConfigBean {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public ShopCart shopCart(){
        return new ShopCart(new ArrayList<>());
    }
}
