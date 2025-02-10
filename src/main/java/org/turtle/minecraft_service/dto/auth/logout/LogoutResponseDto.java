package org.turtle.minecraft_service.dto.auth.logout;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "LogoutResponseDto")
public class LogoutResponseDto {

    @Schema(description = "응답 메시지", example = "성공적으로 로그아웃하였습니다!")
    private String message;

    public static LogoutResponseDto createNewResponse(){
        return LogoutResponseDto.builder()
                .message("성공적으로 로그아웃하였습니다!")
                .build();
    }
}
