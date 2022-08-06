package online.store.onlineBookStore.web.interceptor;

import online.store.onlineBookStore.config.MailConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderEmailSendInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
        MailConfiguration.sendEmail("dani.grozdanov@gmail.com");
        HandlerInterceptor.super.postHandle(request, response, object, modelAndView);
    }
}
