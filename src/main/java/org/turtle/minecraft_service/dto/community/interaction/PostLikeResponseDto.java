package org.turtle.minecraft_service.dto.community.interaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "PostLikeResponseDto")
public class PostLikeResponseDto {

    @Schema(description = "응답 메시지", example = "게시물에 좋아요를 누르셨습니다!")
    private String message;

    public static PostLikeResponseDto createNewResponse(String message){
        return PostLikeResponseDto.builder()
                .message(message)
                .build();
    }
}
