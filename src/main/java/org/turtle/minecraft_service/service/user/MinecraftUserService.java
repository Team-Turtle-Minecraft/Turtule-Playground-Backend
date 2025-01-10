package org.turtle.minecraft_service.service.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.turtle.minecraft_service.config.HttpErrorCode;
import org.turtle.minecraft_service.exception.HttpErrorException;
import reactor.core.publisher.Mono;

@Service
public class MinecraftUserService {

    @Value("${minecraft.attendance.secret}")
    private String apiKey;


    private final WebClient webClient;

    public MinecraftUserService(@Qualifier("turtlePlayGroundWebClient") WebClient webClient) {this.webClient = webClient;}

    public String giveRewardToUser(String nickname){
        return webClient.post()
                .uri("/command")
                .header("X-API-Key", apiKey)
                .bodyValue("give " + nickname + " diamond 1")
                .retrieve()
                .onStatus(status -> status.value() == 401,
                        this::handle401Error)
                .onStatus(status -> status.value() == 403,
                        this::handle403Error)
                .bodyToMono(String.class)
                .block();

    }

    private Mono<Throwable> handle401Error(ClientResponse response) {
        return Mono.error(new HttpErrorException(HttpErrorCode.UnauthorizedTurtlePlayGroundError));
    }

    private Mono<Throwable> handle403Error(ClientResponse response) {
        return Mono.error(new HttpErrorException(HttpErrorCode.ForbiddenTurtlePlayGroundError));
    }
}
