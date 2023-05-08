package com.csgo;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Data
@Component
public class AppConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

}
