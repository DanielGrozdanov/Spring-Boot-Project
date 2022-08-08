package online.store.onlineBookStore.config;

import online.store.onlineBookStore.web.interceptor.OrderEmailSendInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
         interceptorRegistry.addInterceptor(new OrderEmailSendInterceptor()).addPathPatterns("/payments/order-completed");
         WebMvcConfigurer.super.addInterceptors(interceptorRegistry);
    }
}
