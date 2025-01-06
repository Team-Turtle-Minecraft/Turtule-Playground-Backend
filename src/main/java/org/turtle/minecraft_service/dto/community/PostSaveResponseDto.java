package org.turtle.minecraft_service.dto.community;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.PostType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "PostSaveResponseDto")
public class PostSaveResponseDto {

    @Schema(description = "응답 메시지", example = "게시물이 성공적으로 등록되었습니다!")
    private String message;

    @Schema(description = "게시물 작성자", example = "kkOma_fan")
    private String creator;

    @Schema(description = "게시물 카테고리", example = "Free")
    private PostType postType;

    @Schema(description = "게시물 제목")
    private String title;

    @Schema(description = "게시물 내용")
    private String content;

    @Schema(description = "게시물 작성시간")
    private LocalDateTime createdAt;

    public static PostSaveResponseDto fromDto(PostSaveDto dto) {
        return PostSaveResponseDto.builder()
                .message("게시물이 성공적으로 등록되었습니다!")
                .creator(dto.getCreator())
                .postType(dto.getPostType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
