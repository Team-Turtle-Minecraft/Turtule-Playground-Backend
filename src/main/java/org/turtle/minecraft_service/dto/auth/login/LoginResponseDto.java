package org.turtle.minecraft_service.dto.auth.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "LoginResponseDto")
public class LoginResponseDto {

    @Schema(description = "응답 메시지", example = "성공적으로 로그인하였습니다!")
    private String message;

    @Schema(description = "서비스 토큰 정보")
    private LoginToken token;

    public static LoginResponseDto fromDto(LoginDto dto){
        return LoginResponseDto.builder()
                .message("성공적으로 로그인하였습니다!")
                .token(dto.getToken())
                .build();
    }
}
