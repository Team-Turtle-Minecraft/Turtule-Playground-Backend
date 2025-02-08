package org.turtle.minecraft_service.dto.community.interaction;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "PostViewResponseDto")
public class PostViewResponseDto {

    @Schema(description = "응답 메시지", example = "게시물의 조회수가 증가하였습니다. 또는 조회수는 3분에 한번 업데이트됩니다.")
    private String message;

    @Schema(description = "현재 게시물 조회수", example = "1")
    private int currentViews;

    public static PostViewResponseDto fromDto(PostViewDto dto){
        return PostViewResponseDto.builder()
                .message("게시물의 조회수가 증가하였습니다.")
                .currentViews(dto.getCurrentViews())
                .build();
    }
}
