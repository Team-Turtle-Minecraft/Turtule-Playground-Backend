package org.turtle.minecraft_service.dto.community.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.constant.PostType;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@Schema(name = "PostUpdateResponseDto")
public class PostUpdateResponseDto {

    @Schema(description = "응답 메시지", example = "성공적으로 게시물을 수정하였습니다.")
    private String message;

    @Schema(description = "수정된 게시물 번호", example = "1")
    private Long postId;

    @Schema(description = "수정된 게시물 작성자", example = "kkOma_fan")
    private String creator;

    @Schema(description = "수정된 게시물 카테고리", example = "Architecture")
    private PostType postType;

    @Schema(description = "수정된 게시물 제목")
    private String title;

    @Schema(description = "수정된 게시물 내용")
    private String content;

    @Schema(description = "게시물 수정 시간")
    private LocalDateTime updatedAt;

    public static PostUpdateResponseDto fromDto(PostUpdateDto dto) {
        return PostUpdateResponseDto.builder()
                .message("성공적으로 게시물을 수정하였습니다.")
                .postId(dto.getPostId())
                .creator(dto.getCreator())
                .postType(dto.getPostType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
