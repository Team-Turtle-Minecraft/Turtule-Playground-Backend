package org.turtle.minecraft_service.dto.auth.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginToken {

    @Schema(description = "서비스 accessToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIy...")
    private String accessToken;

    @Schema(description = "서비스 refreshToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3...")
    private String refreshToken;

    public static LoginToken of(String accessToken, String refreshToken) {
        return LoginToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
