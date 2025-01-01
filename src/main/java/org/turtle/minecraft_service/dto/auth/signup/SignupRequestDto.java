package org.turtle.minecraft_service.dto.auth.signup;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.turtle.minecraft_service.constant.SnsType;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SignupRequestDto {

    @Schema(example = "Kakao", description = "토큰 발행 기관")
    @NotNull(message = "소셜 계정의 타입이 필요합니다.")
    private SnsType snsType;

    @Schema(example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIy...", description = "토큰 발행 기관에서 받은 AccessToken")
    @NotNull(message = "accessToken이 필요합니다.")
    private String accessToken;

    @Schema(example = "kkOma_fan", description = "거북이 놀이터 서버에서 사용하고 있는 닉네임")
    @NotNull(message = "닉네임이 필요합니다.")
    @Size(min = 2, max = 16, message = "닉네임은 2글자에서 16글자만 허용됩니다.")
    private String nickname;

}
