package org.turtle.minecraft_service.dto.auth.tokenReIssue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "TokenReIssueResponseDto")
public class TokenReIssueResponseDto {

    @Schema(description = "응답 메시지", example = "토큰이 재발행되었습니다!")
    private String message;

    @Schema(description = "재발행된 서비스 accessToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIy...")
    private String accessToken;

    public static TokenReIssueResponseDto fromDto(TokenReIssueDto dto){
        return TokenReIssueResponseDto.builder()
                .message(dto.getMessage())
                .accessToken(dto.getAccessToken())
                .build();
    }
}
