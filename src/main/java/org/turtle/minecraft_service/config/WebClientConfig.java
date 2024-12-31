package org.turtle.minecraft_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.turtle.minecraft_service.constant.SnsBaseUrl;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient googleWebClient(){
        return WebClient.builder()
                .baseUrl(SnsBaseUrl.GoogleBaseUrl.getUrl())
                .build();
    }

    @Bean
    public WebClient kakaoWebClient() {
        return WebClient.builder()
                .baseUrl(SnsBaseUrl.KakaoBaseUrl.getUrl())
                .build();
    }

    @Bean
    public WebClient naverWebClient(){
        return WebClient.builder()
                .baseUrl(SnsBaseUrl.NaverBaseUrl.getUrl())
                .build();
    }
}
