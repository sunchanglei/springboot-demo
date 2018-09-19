package com.boot.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfigure {

    @Bean
    public ServletListenerRegistrationBean<MyHttpSessionListener> serssionListenerBean(){
        ServletListenerRegistrationBean<MyHttpSessionListener>
                sessionListener = new ServletListenerRegistrationBean<MyHttpSessionListener>(new MyHttpSessionListener());
        return sessionListener;
    }


}
