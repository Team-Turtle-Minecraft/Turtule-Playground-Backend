package org.turtle.minecraft_service.dto.auth.signup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.dto.auth.login.LoginToken;

@Getter
@AllArgsConstructor
@Builder
public class SignupDto {

    private LoginToken token;

    public static SignupDto of(String accessToken, String refreshToken) {
        return SignupDto.builder()
                .token(LoginToken.of(accessToken, refreshToken))
                .build();

    }

}
