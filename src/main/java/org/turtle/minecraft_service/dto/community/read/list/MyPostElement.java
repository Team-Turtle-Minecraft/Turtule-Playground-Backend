package org.turtle.minecraft_service.dto.community.read.list;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.turtle.minecraft_service.domain.primary.community.Post;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyPostElement {

    @Schema(description = "게시물 고유번호", example = "1")
    private Long postId;

    @Schema(description = "게시물 제목", example = "마크하다가 개쩌는거 발견함 ㅋㅋ")
    private String title;

    @Schema(description = "게시물 작성자", example = "_appli_")
    private String creator;

    @Schema(description = "게시물 생성시간", example = "2025-01-09 15:45:02.529868")
    private LocalDateTime createdAt;

    public static MyPostElement fromEntity(Post post){
        return MyPostElement.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .creator(post.getUser().getNickname())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
