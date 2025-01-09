package org.turtle.minecraft_service.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.dto.auth.oauth.google.GoogleUserInfoResponse;
import org.turtle.minecraft_service.dto.auth.oauth.kakao.KakaoUserInfoResponse;
import org.turtle.minecraft_service.exception.HttpErrorException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class GoogleOAuthService {

    private final WebClient webClient;

    public GoogleOAuthService(@Qualifier("googleWebClient") WebClient webClient){ this.webClient = webClient; }

    public GoogleUserInfoResponse getUserInfo(String accessToken){
        return webClient.get()
                .uri("oauth2/v3/userinfo")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .onStatus(status -> status.value() == 401,
                        this::handle401Error)
                .onStatus(status -> status.value() == 403,
                        this::handle403Error)
                .bodyToMono(GoogleUserInfoResponse.class)
                .block();
    }

    private Mono<Throwable> handle401Error(ClientResponse response) {
        return Mono.error(new HttpErrorException(HttpErrorCode.UnauthorizedGoogleError));
    }

    private Mono<Throwable> handle403Error(ClientResponse response) {
        return Mono.error(new HttpErrorException(HttpErrorCode.ForbiddenGoogleError));
    }
}