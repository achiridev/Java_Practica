package com.achiridev.proyecto03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.achiridev.proyecto03")
public class AppConfig {
    
    @Bean
    @Primary
    public NotificationSender notificationSender() {
        return new EmailSender();
    }
}
